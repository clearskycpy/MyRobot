package cn.clearskycpy.myrobot.service.chat;

import cn.clearskycpy.myrobot.common.dto.ChatDto;
import cn.clearskycpy.myrobot.common.myexception.ChatException;

/**
 * @description:  聊天对话业务接口
 * @author：clearSky
 * @date: 2023/9/16
 * @Copyright： clearskycpy.cn
 */
public interface IChatService {

    /**
     * 发送消息
     * @param chatDto
     * @return  问题回答
     */
    String sendMessage(ChatDto chatDto) throws Exception;
}
