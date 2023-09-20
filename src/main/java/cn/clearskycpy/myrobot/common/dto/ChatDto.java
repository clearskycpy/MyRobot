package cn.clearskycpy.myrobot.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description:  chat业务传输对象
 * @author：clearSky
 * @date: 2023/9/16
 * @Copyright： clearskycpy.cn
 */
@Data
@ApiModel("ChatDto  问题传输对象")
public class ChatDto {
    @ApiModelProperty(value = "用户id", notes = "当前用户的uId", required = true)
    private Long uId;

    @ApiModelProperty(value = "问题", notes = "提问主体内容", required = true)
    private String question;

    @ApiModelProperty(value = "会话id", notes = "当前使用的会话id", required = true)
    private Long sessionId;
}
