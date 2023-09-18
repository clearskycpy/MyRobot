package cn.clearskycpy.myrobot.chat;

import cn.clearskycpy.myrobot.common.dto.ChatDto;
import cn.clearskycpy.myrobot.common.myexception.ChatException;
import cn.clearskycpy.myrobot.common.po.Session;
import cn.clearskycpy.myrobot.common.po.User;
import cn.clearskycpy.myrobot.id.IdGenerator;
import cn.clearskycpy.myrobot.service.chat.IChatService;
import cn.clearskycpy.myrobot.service.session.ISessionService;
import cn.clearskycpy.myrobot.service.user.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @description:  聊天测试类
 * @author：clearSky
 * @date: 2023/9/16
 * @Copyright： clearskycpy.cn
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ChatTest {
    @Resource
    IChatService chatService;

    @Resource
    IUserService userService;

    @Resource
    ISessionService sessionService;



    private Logger logger = LoggerFactory.getLogger(IdGenerator.class);


    @Test
    public void chatTest1() throws Exception {
        ChatDto chatDto = new ChatDto();
        chatDto.setQuestion("告诉我我的上一个问题");
        chatDto.setSessionId(112L);
        chatDto.setUId(1702515013501222912L);
        chatService.sendMessage(chatDto);
    }

    /**
     *
     */
    @Test
    public void insertUser() {
        User user = new User();
        user.setUserName("admin");
        user.setPassword("011204");
        user.setPhone("18679516070");
        userService.insertUser(user);

    }


    @Test
    public void insertSession () {
        Session session = new Session();
        session.setuId(1703569260854542336L);
        session.setSessionName("java注解使用");
        sessionService.insertSession(session);
    }

    /**
     *
     */
    @Test
    public void chatSend() throws Exception {
        ChatDto chatDto = new ChatDto();
        chatDto.setUId(1703569260854542336L);
        chatDto.setQuestion("");
        chatDto.setSessionId(1703571090342838272L);
        String message = chatService.sendMessage(chatDto);
        logger.info(message);
    }
}
