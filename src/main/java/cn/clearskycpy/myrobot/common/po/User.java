package cn.clearskycpy.myrobot.common.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类
 * @author clearSky
 */
@ApiModel("用户实体类")
public class User implements Serializable {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "自增id")
    private Long id;

    @ApiModelProperty(value = "用户id")
    private Long uId;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "账号状态 1 可用 2 禁用 3 删除")
    private Integer state;

    @ApiModelProperty(value = "用户类型 1 普通用户 2  会员用户 3 管理员用户 4 root用户")
    private Integer userType;

    @ApiModelProperty(value = "问题使用次数  普通用户当用量为 0 时，新的问题不再回答  会员用户不限制")
    private Integer messageCnt;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getuId() {
        return uId;
    }

    public void setuId(Long uId) {
        this.uId = uId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getMessageCnt() {
        return messageCnt;
    }

    public void setMessageCnt(Integer messageCnt) {
        this.messageCnt = messageCnt;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}