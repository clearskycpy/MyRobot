package cn.clearskycpy.myrobot.controller;

import cn.clearskycpy.myrobot.common.Constants;
import cn.clearskycpy.myrobot.common.Result;
import cn.clearskycpy.myrobot.common.dto.UserResDto;
import cn.clearskycpy.myrobot.common.po.User;
import cn.clearskycpy.myrobot.common.vo.UserVo;
import cn.clearskycpy.myrobot.service.user.IUserService;
import cn.hutool.core.util.ObjectUtil;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

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
public class UserController {

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
//        TODO 验证码验证
        if(!queryCode(userVo.getMessageCode())) {
            return Result.error(Constants.ResponseCode.CODE_ERROR.getCode(), Constants.ResponseCode.CODE_ERROR.getInfo());
        }
//        TODO 数据校验
        User user = userService.queryUser(userVo);
        if (user != null) {
//           登录成功  将userID存入 session中
            httpSession.setAttribute(Constants.LOGIN, user.getuId());
            return Result.success(user);
        }

        //        登录校验
        return Result.error(Constants.ResponseCode.UN_ERROR.getCode(),Constants.ResponseCode.UN_ERROR.getInfo());
    }

    /**
     * 登录接口
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
//        TODO 验证码校验
        if(!queryCode(userVo.getMessageCode())) {
            return Result.error(Constants.ResponseCode.CODE_ERROR.getCode(), Constants.ResponseCode.CODE_ERROR.getInfo());
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

    @GetMapping("/sendCodeMessage/{phone}")
    @ApiOperation(value = "发送短信验证码 给当前提供的手机号", notes = "默认123456,暂时未实现验证码发送")
    public Result sendMessageCode(@PathVariable("phone") String phone) {
        return Result.success();
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
