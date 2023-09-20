package cn.clearskycpy.myrobot.common;

import cn.clearskycpy.myrobot.common.Constants.ResponseCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @description: 统一返回对象
 * @author：ClearSky
 * @date: 2023/8/25/025
 * @Copyright： https://clearskycpy.cn
 */
@ApiModel(value = "Result  统一返回对象", description = "统一返回对象")
public class Result<T> {

    /**
     * 是否成功
     */
    @ApiModelProperty(value = "是否成功",notes = "true 为成功 false 为不成功")
    private Boolean success;

    /**
     * 状态码
     */
    @ApiModelProperty(value = "状态码", notes = "200 为成功，其余为失败")
    private String code;

    /**
     * 提示信息
     */
    @ApiModelProperty(value = "提示信息",notes = "如果出现错误时，返回相对应的提示信息")
    private String msg;

    /**
     * 数据
     */
    @ApiModelProperty(value = "数据集合",notes = "当期响应如果成功，有对应的数据集合应当在这里面反馈")
    private T data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.code = "200";
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> Result<T> success(T data) {
        return success(data, Constants.ResponseCode.SUCCESS.getInfo());
    }

    public static <T> Result<T> success(T data, String msg) {
        Result<T> result = new Result<>();
        result.setSuccess(Boolean.TRUE);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.setSuccess(Boolean.TRUE);
        return result;
    }

    public static <T> Result<T> error(String msg) {
        return error(Constants.ResponseCode.UN_ERROR.getCode(), msg);
    }

    public static <T> Result<T> error(String code, String msg) {
        return error(code, msg, null);
    }

    public static <T> Result<T> error(String code, String msg, T data) {
        Result<T> result = new Result<>();
        result.setSuccess(Boolean.FALSE);
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
}
