package cn.clearskycpy.myrobot.listener;

import cn.clearskycpy.myrobot.api.BigModelNew;
import cn.clearskycpy.myrobot.common.Constants;
import cn.clearskycpy.myrobot.common.vo.res.JsonParse;
import cn.clearskycpy.myrobot.common.vo.res.Text;
import cn.clearskycpy.myrobot.context.MessageContext;
import com.google.gson.Gson;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author 对话监听器
 */
@Component
public class MyWebSocketListener extends WebSocketListener {
    private CountDownLatch latch;

    public static final Gson gson = new Gson();

    @Resource
    private StringBuffer message;

    public void waitForMessageCompletion() throws InterruptedException {
        latch = new CountDownLatch(1);
        // 另当前线程等待30秒，等不到自动释放
        latch.await(30, TimeUnit.SECONDS);
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        JsonParse myJsonParse = gson.fromJson(text, JsonParse.class);
        if (myJsonParse.getHeader().getCode() != 0) {
            System.out.println("发生错误，错误码为：" + myJsonParse.getHeader().getCode());
            System.out.println("本次请求的sid为：" + myJsonParse.getHeader().getSid());
            webSocket.close(1000, "");
        }
        List<Text> textList = myJsonParse.getPayload().getChoices().getText();
        for (Text temp : textList) {
            message.append(temp.getContent());
            // 写入
        }
        if (myJsonParse.getHeader().getStatus() == 2) {
            // 可以关闭连接，释放资源
            System.out.println("当前回答已写完");
            latch.countDown();
        }
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        super.onFailure(webSocket, t, response);
        try {
            if (null != response) {
                int code = response.code();
                System.out.println("onFailure code:" + code);
                System.out.println("onFailure body:" + response.body().string());
                if (101 != code) {
                    System.out.println("connection failed");
                    System.exit(0);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
