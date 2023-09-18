package cn.clearskycpy.myrobot.common.vo.req;

import lombok.Data;

/**
 * @description:
 * @author：ClearSky
 * @date: 2023/8/25/025
 * @Copyright： https://clearskycpy.cn
 */
@Data
public class Text {
    private String role;
    private String content;

    public Text(String role, String content) {
        this.role = role;
        this.content = content;
    }

    public Text() {
    }
}
