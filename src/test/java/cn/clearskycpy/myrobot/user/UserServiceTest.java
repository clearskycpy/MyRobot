package cn.clearskycpy.myrobot.user;

import cn.clearskycpy.myrobot.common.Constants;
import cn.clearskycpy.myrobot.common.po.User;
import cn.clearskycpy.myrobot.service.user.IUserService;
import org.junit.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @description:  user 业务类测试
 * @author：clearSky
 * @date: 2023/9/15
 * @Copyright： clearskycpy.cn
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {
    Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    @Resource
    IUserService userService;

    @Test
    public void insert () {
        User user = new User();
        user.setUserName("MY_TEST");
        user.setPassword("123456");
        user.setPhone("12345678909");
        userService.insertUser(user);
    }

    @Test
    public void consumptionMessageTest() {
        userService.consumptionMessage(1702515013501222912L);
    }
}
