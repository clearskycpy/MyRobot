package cn.clearskycpy.myrobot.service.apisupport;

/**
 * @description: 讯飞api支持
 * @author：ClearSky
 * @date: 2023/8/25/025
 * @Copyright： https://clearskycpy.cn
 */
public interface IApiSupport {
    /**
     * 提问
     * @param question
     * @return  问题答案
     */
    String askQuestion(String question);
}
