package cn.clearskycpy.myrobot.service.user.impl;

import cn.clearskycpy.myrobot.common.Constants;
import cn.clearskycpy.myrobot.common.dto.UserResDto;
import cn.clearskycpy.myrobot.common.po.User;
import cn.clearskycpy.myrobot.common.support.ids.IIdGenerator;
import cn.clearskycpy.myrobot.common.utils.MD5Utils;
import cn.clearskycpy.myrobot.mapper.UserMapper;
import cn.clearskycpy.myrobot.service.user.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

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

    @Resource
    Map<Constants.Ids, IIdGenerator> idGeneratorMap;

    @Override
    public boolean switchUserState(Constants.UserState targetUserState) {
        return false;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public Integer queryMessageCntByUId(Long uId) {
        return null;
    }

    @Override
    public void consumptionMessage(Long uId) {

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
}
