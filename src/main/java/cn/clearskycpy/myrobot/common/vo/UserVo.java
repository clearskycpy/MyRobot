package cn.clearskycpy.myrobot.common.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description:  user数据传输对象
 * @author：clearSky
 * @date: 2023/9/18
 * @Copyright： clearskycpy.cn
 */
@ApiModel("用户数据传输对象")
@Data
public class UserVo implements Serializable {

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "验证码")
    private String messageCode;

}
