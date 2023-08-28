package cn.clearskycpy.myrobot;

import cn.clearskycpy.myrobot.config.MyUrlSource;
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
    @Test
    void contextLoads() throws Exception {
        System.out.println(urlConfig.getUrl());
    }
}
