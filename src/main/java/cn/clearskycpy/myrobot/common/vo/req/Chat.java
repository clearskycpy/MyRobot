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
}
