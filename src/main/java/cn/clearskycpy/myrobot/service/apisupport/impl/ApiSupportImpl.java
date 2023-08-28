package cn.clearskycpy.myrobot.service.apisupport.impl;

import cn.clearskycpy.myrobot.config.MyUrlSource;
import cn.clearskycpy.myrobot.service.apisupport.IApiSupport;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description:
 * @author：ClearSky
 * @date: 2023/8/25/025
 * @Copyright： https://clearskycpy.cn
 */
@Service
public class ApiSupportImpl implements IApiSupport {

    @Resource
    private MyUrlSource myUrlSource;
    @Override
    public String askQuestion(String question) {
        try {
            // 获取url
            String url = myUrlSource.getUrl();
            OkHttpClient client = new OkHttpClient.Builder().build();
            Request request = new Request.Builder().url(url).build();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
