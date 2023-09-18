package cn.clearskycpy.myrobot.common.myexception;

import cn.clearskycpy.myrobot.common.Constants;

/**
 * @description:
 * @author：clearSky
 * @date: 2023/9/16
 * @Copyright： clearskycpy.cn
 */
public class ChatException extends Exception{
    public ChatException (Constants.ChatExceptionType exceptionType) {
        super(exceptionType.getMessage());
    }
}
