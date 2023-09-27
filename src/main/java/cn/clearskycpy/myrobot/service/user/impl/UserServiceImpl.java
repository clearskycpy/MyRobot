package cn.clearskycpy.myrobot.service.user.impl;

import cn.clearskycpy.myrobot.common.Constants;
import cn.clearskycpy.myrobot.common.Result;
import cn.clearskycpy.myrobot.common.dto.UserResDto;
import cn.clearskycpy.myrobot.common.po.User;
import cn.clearskycpy.myrobot.common.support.ids.IIdGenerator;
import cn.clearskycpy.myrobot.common.utils.MD5Utils;
import cn.clearskycpy.myrobot.common.utils.RegexUtils;
import cn.clearskycpy.myrobot.common.utils.SMSUtils;
import cn.clearskycpy.myrobot.common.vo.UserVo;
import cn.clearskycpy.myrobot.mapper.UserMapper;
import cn.clearskycpy.myrobot.service.user.IUserService;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static cn.clearskycpy.myrobot.common.Constants.LOGIN_CODE_KEY;
import static cn.clearskycpy.myrobot.common.Constants.LOGIN_CODE_TTL;

/**
 * @description:  用户相关业务类
 * @author：clearSky
 * @date: 2023/9/14
 * @Copyright： clearskycpy.cn
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    Map<Constants.Ids, IIdGenerator> idGeneratorMap;

    /**
     * 切换用户状态
     * @param targetUserState  目标状态
     * @return
     */
    @Override
    public UserResDto switchUserState(Constants.UserState targetUserState, Long uId) {
        LambdaUpdateWrapper<User> userLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        userLambdaUpdateWrapper.set(User::getState,targetUserState.getCode()).eq(User::getuId, uId);
        userMapper.update(null, userLambdaUpdateWrapper);
        return new UserResDto(true);
    }


    @Override
    public void update(User user) {
        LambdaUpdateWrapper<User> userUpdateWrapper = new LambdaUpdateWrapper<>();
        userUpdateWrapper.eq(User::getPhone,user.getPhone());
        userMapper.update(user,userUpdateWrapper);
        return;
    }

    @Override
    public Integer queryMessageCntByUId(Long uId) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getuId, uId);
        return userMapper.selectOne(queryWrapper).getMessageCnt();
    }

    @Override
    public void consumptionMessage(Long uId) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getuId, uId);
        User user = userMapper.selectOne(queryWrapper);
        user.setMessageCnt(user.getMessageCnt() - 1);
//        int i = 1/0;
        userMapper.update(user,queryWrapper);
        return ;
    }

    /**
     * 用户创建方法  校验手机号是否唯一 默认创建普通用户  密码使用md5 加密
     * @param user
     */
    @Override
    public UserResDto insertUser(User user) {
//        检验手机号
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getPhone,user.getPhone());
        Integer cntPhone = userMapper.selectCount(queryWrapper);
        if (cntPhone.intValue() != 0) {
            return new UserResDto(false,"手机号重复");
        }
        // 封装user
        String oldPassWord = user.getPassword();
        user.setPassword(MD5Utils.encrypt(oldPassWord));
        user.setuId(idGeneratorMap.get(Constants.Ids.SnowFlake).nextId());
        user.setMessageCnt(Constants.MESSAGE_DEFAULT_VALUE);
        user.setState(Constants.UserState.DOING.getCode());
        user.setUserType(Constants.UserType.USER_BASIS.getCode());
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userMapper.insert(user);
        return new UserResDto(true);
    }

    @Override
    public User queryUser(UserVo userVo) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getPhone,userVo.getPhone()).eq(User::getPassword,MD5Utils.encrypt(userVo.getPassword())).eq(User::getState,Constants.UserState.DOING.getCode());
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public Result sendcode(String phone, HttpSession session) {
        // 1.校验手机号
        if (RegexUtils.isPhoneInvalid(phone)) {
            // 2.如果不符合，返回错误信息
            return Result.error("手机号格式错误！");
        }
        // 3.符合生成验证码,RandomUtil是hutool-all的工具类
        String code = RandomUtil.randomNumbers(6);
        // 4.保存验证码到redis
        stringRedisTemplate.opsForValue().set(LOGIN_CODE_KEY + phone, code, LOGIN_CODE_TTL, TimeUnit.MINUTES);
        // 发生验证码
        SMSUtils.sendMessage("阿里云短信测试","SMS_154950909",phone,code);
        // 6.返回成功信息
        return Result.success();
    }

    @Override
    public Result login(UserVo userVo, HttpSession httpSession) {
        //        TODO 验证码验证
        /*if(!queryCode(userVo.getMessageCode())) {
            return Result.error(Constants.ResponseCode.CODE_ERROR.getCode(), Constants.ResponseCode.CODE_ERROR.getInfo());
        }*/// 1.校验手机号的格式
        String phone = userVo.getPhone();
        if (RegexUtils.isPhoneInvalid(phone)) {
            // 2.如果不符合，返回错误信息
            return Result.error("手机号格式错误！");
        }

        // 3.从redis获取验证码并和用户提交的验证码校验比对
        String cacheCode = stringRedisTemplate.opsForValue().get(LOGIN_CODE_KEY + phone);
        String code = userVo.getMessageCode();
        if (cacheCode == null || !cacheCode.equals(code)) {
            // 不一致则报错
            return Result.success("验证码错误");
        }

        // 验证码一致则根据手机号查询用户
        User user = query().eq("phone", phone).one();
        //5.判断用户是否存在
        if(user == null){
            //不存在，则创建
            user =  createUserWithPhone(phone);
        }
        // 6.登陆成功则删除验证码信息
        stringRedisTemplate.delete(LOGIN_CODE_KEY + phone);



//        TODO 数据校验
        user = queryUser(userVo);
        if (user != null) {
//           登录成功  将userID存入 session中
            httpSession.setAttribute(Constants.LOGIN, user.getuId());
            return Result.success(user);
        }

        //        登录校验
        return Result.error(Constants.ResponseCode.UN_ERROR.getCode(),Constants.ResponseCode.UN_ERROR.getInfo());
    }

    private User createUserWithPhone(String phone) {
        //创建用户
        User user = new User();
        //设置手机号
        user.setPhone(phone);
        //保存到数据库
        save(user);
        return user;
    }
}
