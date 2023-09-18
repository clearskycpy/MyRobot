package cn.clearskycpy.myrobot.common.vo.req;

import lombok.Data;

/**
 * @description:
 * @author：ClearSky
 * @date: 2023/8/25/025
 * @Copyright： https://clearskycpy.cn
 */
@Data
public class Chat {
    private String domain;
    private Double temperature;
    private Integer max_tokens;

    public Chat(String domain, Double temperature, Integer max_tokens) {
        this.domain = domain;
        this.temperature = temperature;
        this.max_tokens = max_tokens;
    }

    public Chat() {
    }
}
