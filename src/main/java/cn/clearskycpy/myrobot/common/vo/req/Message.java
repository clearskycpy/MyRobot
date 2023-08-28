package cn.clearskycpy.myrobot.common.vo.req;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author：ClearSky
 * @date: 2023/8/25/025
 * @Copyright： https://clearskycpy.cn
 */
@Data
public class Message {
    private List<Text> text;
}
