package cn.clearskycpy.myrobot.common.dto;

import lombok.Data;

/**
 * @description:  user 传输返回对象
 * @author：clearSky
 * @date: 2023/9/15
 * @Copyright： clearskycpy.cn
 */
@Data
public class UserResDto {
    private boolean successState;
    private String message;

    public UserResDto(boolean successState, String message) {
        this.successState = successState;
        this.message = message;
    }

    public UserResDto(boolean successState) {
        this.successState = successState;
    }
}
