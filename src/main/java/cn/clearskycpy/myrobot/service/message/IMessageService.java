package cn.clearskycpy.myrobot.service.message;

import cn.clearskycpy.myrobot.common.dto.ChatDto;
import cn.clearskycpy.myrobot.common.po.Message;
import cn.clearskycpy.myrobot.common.po.Session;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @description:  问答消息业务处理类
 * @author：clearSky
 * @date: 2023/9/15
 * @Copyright： clearskycpy.cn
 */
public interface IMessageService extends IService<Message> {
    /**
     * 根据uId 和 当前的会话id 获取 历史消息列表
     * @return  消息列表
     */
    List<Message> getMessageListBySessionAndUId(Long uId, Long sessionId);


    /**
     * 插入消息
     * @param chatDto  对话数据传输对象
     * @param updateSession  更新后的对话信息
     * @param answerResult 响应的回答
     */
    void insertMessage(ChatDto chatDto, Session updateSession, String answerResult);
}
