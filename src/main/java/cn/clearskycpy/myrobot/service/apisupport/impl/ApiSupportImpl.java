package cn.clearskycpy.myrobot.service.apisupport.impl;

import cn.clearskycpy.myrobot.common.Constants;
import cn.clearskycpy.myrobot.context.MessageContext;
import cn.clearskycpy.myrobot.listener.MyWebSocketListener;
import cn.clearskycpy.myrobot.config.MyUrlSource;
import cn.clearskycpy.myrobot.service.apisupport.IApiSupport;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @description:  讯飞api 操作
 * @author：ClearSky
 * @date: 2023/8/25/025
 * @Copyright： https://clearskycpy.cn
 */
@Service
public class ApiSupportImpl implements IApiSupport {

    @Resource
    private MyUrlSource myUrlSource;

    @Resource
    private OkHttpClient client;

    @Resource
    private MyWebSocketListener myWebSocketUtil;

    @Resource
    private StringBuffer message;

    @Override
    public String askQuestion(String question) {
        try {
//          初始化写入状态 , 清空原始字符串
            message.delete(0,message.length());
            // 获取url
            String url = myUrlSource.getAuthUrl();
            Request request = new Request.Builder().url(url).build();
            WebSocket webSocket = client.newWebSocket(request, myWebSocketUtil);
            String requestJson = "{\n" +
                    "  \"header\": {\n" +
                    "    \"app_id\": \"" + "adecb169" + "\",\n" +
                    "    \"uid\": \"" + UUID.randomUUID().toString().substring(0, 10) + "\"\n" +
                    "  },\n" +
                    "  \"parameter\": {\n" +
                    "    \"chat\": {\n" +
                    "      \"domain\": \"general\",\n" +
                    "      \"temperature\": 0.5,\n" +
                    "      \"max_tokens\": 1024\n" +
                    "    }\n" +
                    "  },\n" +
                    "  \"payload\": {\n" +
                    "    \"message\": {\n" +
                    "      \"text\": [\n" +
                    "        {\n" +
                    "          \"role\": \"user\",\n" +
                    "          \"content\": \"中国第一个皇帝是谁？\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"role\": \"assistant\",\n" +
                    "          \"content\": \"秦始皇\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"role\": \"user\",\n" +
                    "          \"content\": \"秦始皇修的长城吗\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"role\": \"assistant\",\n" +
                    "          \"content\": \"是的\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"role\": \"user\",\n" +
                    "          \"content\": \"" + question + "\"\n" +
                    "        }\n" +
                    "      ]\n" +
                    "    }\n" +
                    "  }\n" +
                    "}";
            webSocket.send(requestJson);
//            Thread.sleep(30000); // 等待30秒钟  ,  改变为判断是否读取完成
            // 等待异步操作完成
            myWebSocketUtil.waitForMessageCompletion();
//            写完了将消息加入对话中
//            返回消息
            webSocket.close(1000, "");
            return message.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
