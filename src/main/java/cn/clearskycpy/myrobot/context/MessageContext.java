package cn.clearskycpy.myrobot.context;

import cn.clearskycpy.myrobot.common.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MessageContext {
    /**
     * 消息体
     */
    @Bean
    public StringBuffer message() {
        return new StringBuffer();
    }
}
