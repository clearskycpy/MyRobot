package cn.clearskycpy.myrobot;

import cn.clearskycpy.myrobot.config.MyUrlSource;
import cn.clearskycpy.myrobot.service.apisupport.IApiSupport;
import cn.clearskycpy.myrobot.common.utils.SMSUtils;
import cn.clearskycpy.myrobot.common.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
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
    @Test
    void testSMS(){
        //生成随机的4位验证码
        String code = ValidateCodeUtils.generateValidateCode(4).toString();
        //自己在后台查看生成的验证码
        log.info("code={}",code);

        //调用SMSUtils工具类中的阿里云提供的短信服务API完成发送短信
        //SMSUtils.sendMessage("签名","模板",phone,code);
        SMSUtils.sendMessage("阿里云短信测试","模板","19812622163","SMS_154950909");

    }
}
