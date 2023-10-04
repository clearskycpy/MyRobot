package cn.clearskycpy.myrobot.controller;

import cn.clearskycpy.myrobot.common.Constants;
import cn.clearskycpy.myrobot.common.Result;
import cn.clearskycpy.myrobot.common.dto.UserResDto;
import cn.clearskycpy.myrobot.common.po.User;
import cn.clearskycpy.myrobot.common.utils.RegexUtils;
import cn.clearskycpy.myrobot.common.vo.UserVo;
import cn.clearskycpy.myrobot.service.user.IUserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import static cn.clearskycpy.myrobot.common.Constants.LOGIN_CODE_KEY;

/**
 * @description:  用户接口
 * @author：clearSky
 * @date: 2023/9/18
 * @Copyright： clearskycpy.cn
 */
@RestController
@RequestMapping("/chat/user")
@Api(tags = "用户接口")
@CrossOrigin
@Slf4j
public class UserController {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Resource
    private IUserService userService;

    /**
     * 登录接口
     * @return
     */
    @ApiOperation(value = "用户登录", notes = "进行用户登录,从数据库校验")
    @PostMapping("/login")
    public Result login(@RequestBody UserVo userVo,
                        @ApiIgnore
                        HttpSession httpSession){
        return userService.login(userVo, httpSession);
    }

    /**
     * 登出接口
     * @return
     */
    @ApiOperation(value = "用户登出", notes = "登出，session去除")
    @PostMapping("/logout")
    public Result logout(@ApiIgnore HttpSession httpSession){
//           登录成功  将userID存入 session中
            httpSession.removeAttribute(Constants.LOGIN);
            return Result.success();
    }



    @ApiOperation(value = "用户更新", notes = "更新用户名和密码  :: 前端手机号无法改动")
    @PostMapping("/update")
    public Result update(@RequestBody UserVo userVo) {
        User user = new User();
        user.setUserName(userVo.getUserName());
        user.setPassword(userVo.getPassword());
        user.setPhone(userVo.getPhone());
        userService.update(user);
        return Result.success();
    }

    /**
     * 用户注册接口
     * @param userVo  用户数据传输对象
     * @return
     */
    @ApiOperation(value = "用户注册", notes = "验证验证码，进行用户注册")
    @PostMapping("/create")
    public Result create(@RequestBody UserVo userVo) {
//        验证码校验
        String cacheCode = stringRedisTemplate.opsForValue().get(LOGIN_CODE_KEY + userVo.getPhone());
        String code = userVo.getMessageCode();
        if (cacheCode == null || !cacheCode.equals(code)) {
            // 不一致则报错
            return Result.success("验证码错误");
        }
        User user = new User();
        user.setUserName(userVo.getUserName());
        user.setPassword(userVo.getPassword());
        user.setPhone(userVo.getPhone());
        UserResDto userResDto = userService.insertUser(user);
        boolean successState = userResDto.getSuccessState();
        if (successState) {
            return Result.success();
        }
        return Result.error(userResDto.getMessage());
    }

    @GetMapping("/sendCodeMessage")
    @ApiOperation(value = "发送短信验证码", notes = "传入一个电话号码 使用阿里云短信发送短信验证码到手机")
    public Result sendMessageCode(@RequestBody UserVo userVo, HttpSession session) {
        return userService.sendcode(userVo.getPhone(), session);
    }


    /**
     * TODO  验证码校验
     * @param messageCode
     * @return
     */
    private boolean queryCode(String messageCode) {
        return "123456".equals(messageCode);
    }



}
