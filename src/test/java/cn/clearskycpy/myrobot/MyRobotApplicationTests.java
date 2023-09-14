package cn.clearskycpy.myrobot;

import cn.clearskycpy.myrobot.config.MyUrlSource;
import cn.clearskycpy.myrobot.service.apisupport.IApiSupport;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
class MyRobotApplicationTests {
    @Resource
    MyUrlSource urlConfig;

    @Resource
    IApiSupport iApiSupport;
    @Test
    void contextLoads() throws Exception {
        System.out.println(urlConfig.getAuthUrl());
    }

    @Test
    void send() {
        System.out.println(iApiSupport.askQuestion("帮我画只狗"));
    }
}
