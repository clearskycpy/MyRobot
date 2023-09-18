package cn.clearskycpy.myrobot.service.chat.impl;

import cn.clearskycpy.myrobot.common.Constants;
import cn.clearskycpy.myrobot.common.dto.ChatDto;
import cn.clearskycpy.myrobot.common.myexception.ChatException;
import cn.clearskycpy.myrobot.common.myexception.UserException;
import cn.clearskycpy.myrobot.common.po.Session;
import cn.clearskycpy.myrobot.common.po.User;
import cn.clearskycpy.myrobot.common.vo.req.*;
import cn.clearskycpy.myrobot.service.apisupport.IApiSupport;
import cn.clearskycpy.myrobot.service.chat.IChatService;
import cn.clearskycpy.myrobot.service.message.IMessageService;
import cn.clearskycpy.myrobot.service.session.ISessionService;
import cn.clearskycpy.myrobot.service.user.IUserService;
import cn.clearskycpy.myrobot.service.user.impl.UserServiceImpl;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mysql.cj.util.StringUtils;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:  聊天对话业务实现类
 * @author：clearSky
 * @date: 2023/9/16
 * @Copyright： clearskycpy.cn
 */
@Service
public class ChatServiceImpl implements IChatService {


    @Value("${XF-data.appid}")
    public String appid;

    @Resource
    IMessageService messageService;

    @Resource
    IApiSupport apiSupport;

    @Resource
    private IUserService userService;

    @Resource
    private ISessionService sessionService;

    /**
     * 发送消息并且持久化消息体
     * @param chatDto  chat数据传输对象
     * @return 返回问题回答后的字符串
     */
    @Override
    @Transactional(rollbackFor = Exception.class) // 添加事务
    public String sendMessage(ChatDto chatDto) throws Exception {
//        用户可消费性校验
        Integer messageCntByUId = userService.queryMessageCntByUId(chatDto.getUId());
        if (messageCntByUId == 0) {
            throw new UserException("可消费余额不足");
        }
//        请求体封装
        ReqJson reqJson = getReqJson(chatDto);

//        请求体转json字符串
        String reqJSONString = JSON.toJSONString(reqJson);

//        发送 并获取 相应消息体
        String answerResult = apiSupport.askQuestion(reqJSONString);

//      未得到回答
        if (StringUtils.isNullOrEmpty(answerResult)) {
            throw new ChatException(Constants.ChatExceptionType.RESPONSE_FAILED);
        }
//        持久化
//        会话记录数加一
        Session updateSession = sessionService.autoincrementMessageCnt(chatDto.getSessionId(), chatDto.getUId());

//        增加Message
        messageService.insertMessage(chatDto, updateSession, answerResult);


//        消费消息
        userService.consumptionMessage(chatDto.getUId());
//        返回结果
        return answerResult;
    }

    /**
     * 获取 请求的 对象
     * @param chatDto
     * @return
     */
    private ReqJson getReqJson(ChatDto chatDto) {
        ReqJson reqJson = new ReqJson();
//        封装头

        Header header = new Header();
        header.setApp_id(appid);
        header.setUid("clearSky_"+ String.valueOf(chatDto.getUId()));
        reqJson.setHeader(header);

//        封装参数
        Parameter parameter = new Parameter();
        Chat chat = new Chat();
        chat.setDomain(Constants.ChatParameter.DEFAULT.getDomain());
        chat.setTemperature(Constants.ChatParameter.DEFAULT.getTemperature());
        chat.setMax_tokens(Constants.ChatParameter.DEFAULT.getMax_tokens());
        parameter.setChat(chat);
        reqJson.setParameter(parameter);

//      封装历史消息 初始化问题流  获取之前的Message
        Payload payload = new Payload();
        Message message1 = new Message();
        List<Text> textList = getTextList(chatDto);

//      封装问题
        Text text = new Text();
        text.setRole(Constants.TextRole.USER.getCode());
        text.setContent(chatDto.getQuestion());
        textList.add(text);

        message1.setText(textList);
        payload.setMessage(message1);

        reqJson.setPayload(payload);
        return reqJson;
    }

    private List<Text> getTextList(ChatDto chatDto) {
        List<Text> textList = new ArrayList<>();
        List<cn.clearskycpy.myrobot.common.po.Message> messageList = messageService.getMessageListBySessionAndUId(chatDto.getUId(), chatDto.getSessionId());
        for (int i = 0; i < messageList.size(); i++) {
//            将每一个 text 封装成两个  Text
            cn.clearskycpy.myrobot.common.po.Message message = messageList.get(i);
            textList.add(new Text(Constants.TextRole.USER.getCode(), message.getMessageQuestion()));
            textList.add(new Text(Constants.TextRole.ASSISTANT.getCode(), message.getMessageAnswer()));
        }
        return textList;
    }
}
