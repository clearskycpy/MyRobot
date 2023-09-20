package cn.clearskycpy.myrobot.common.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @author clearSky
 */
@ApiModel("会话实体类")
public class Session implements Serializable {

    @ApiModelProperty(value = "自增id")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户id")
    private Long uId;

    @ApiModelProperty(value = "会话id")
    private Long sessionId;

    @ApiModelProperty(value = "会话名称")
    private String sessionName;

    @ApiModelProperty(value = "会话记录数")
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

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName == null ? null : sessionName.trim();
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