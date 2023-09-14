package cn.clearskycpy.myrobot.config;

import cn.clearskycpy.myrobot.listener.MyWebSocketListener;
import okhttp3.OkHttpClient;
import okhttp3.WebSocketListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebSocketClientConfig {
    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder().build();
    }

    @Bean
    public WebSocketListener webSocketListener() {
        return new MyWebSocketListener(); // 替换成你自己的 WebSocketListener
    }
}
