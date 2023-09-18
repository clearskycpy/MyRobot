package cn.clearskycpy.myrobot.common.dto;

import lombok.Data;

/**
 * @description:  chat业务传输对象
 * @author：clearSky
 * @date: 2023/9/16
 * @Copyright： clearskycpy.cn
 */
@Data
public class ChatDto {
    private Long uId;
    private String question;
    private Long sessionId;
}
