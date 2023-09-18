package cn.clearskycpy.myrobot.service.message.impl;

import cn.clearskycpy.myrobot.common.dto.ChatDto;
import cn.clearskycpy.myrobot.common.po.Message;
import cn.clearskycpy.myrobot.common.po.Session;
import cn.clearskycpy.myrobot.mapper.MessageMapper;
import cn.clearskycpy.myrobot.service.message.IMessageService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @description:  消息处理业务实现类
 * @author：clearSky
 * @date: 2023/9/15
 * @Copyright： clearskycpy.cn
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

    @Resource
    private MessageMapper messageMapper;

    @Override
    public List<Message> getMessageListBySessionAndUId(Long uId, Long sessionId) {
        LambdaQueryWrapper<Message> queryWrapper = new LambdaQueryWrapper<>();
//        按照Message的 id 进行排序
        queryWrapper.eq(Message::getuId,uId).eq(Message::getSessionId,sessionId).orderByAsc(Message::getMessageId);
        List<Message> list = messageMapper.selectList(queryWrapper);
        return list;
    }

    @Override
    public void insertMessage(ChatDto chatDto, Session updateSession, String answerResult) {
        Message message = new Message();
        message.setuId(chatDto.getUId());
        message.setMessageId(updateSession.getMessageCnt());
        message.setMessageQuestion(chatDto.getQuestion());
        message.setMessageAnswer(answerResult);
        message.setCreateTime(new Date());
        message.setUpdateTime(new Date());
        message.setSessionId(updateSession.getSessionId());
        messageMapper.insert(message);
    }
}
