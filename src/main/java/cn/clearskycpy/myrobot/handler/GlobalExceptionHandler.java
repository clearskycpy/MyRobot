package cn.clearskycpy.myrobot.handler;

import cn.clearskycpy.myrobot.common.Result;
import cn.clearskycpy.myrobot.common.myexception.ChatException;
import cn.clearskycpy.myrobot.common.myexception.UserException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @description:
 * @author：clearSky
 * @date: 2023/9/15
 * @Copyright： clearskycpy.cn
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
public class GlobalExceptionHandler {
    // 处理唯一性字段发生重复的异常 （完整性约束返回异常）
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Result<String> exceptionHandler(SQLIntegrityConstraintViolationException e){
        if (e.getMessage().contains("Duplicate entry")){
            String[] split = e.getMessage().split(" ");
            String msg = split[2] + "已存在";
            return Result.error(msg);
        }
        return Result.error("网络异常");
    }

    /**
     *  用户service异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(UserException.class)
    public Result<String> exceptionHandler(UserException e){
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(ChatException.class)
    public Result<String> exceptionHandler(ChatException e) {
        return Result.error(e.getMessage());
    }

    /*@ExceptionHandler(Exception.class)
    public Result<String> exceptionHandler(Exception e) {
        return Result.error("异常情况无法读取数据");
    }*/

}
