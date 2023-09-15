package cn.clearskycpy.myrobot.common.po;



import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long uId;

    private Integer messageId;

    private Long sessionId;

    private Date createTime;

    private Date updateTime;

    private String messageQuestion;

    private String messageAnswer;

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

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
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

    public String getMessageQuestion() {
        return messageQuestion;
    }

    public void setMessageQuestion(String messageQuestion) {
        this.messageQuestion = messageQuestion == null ? null : messageQuestion.trim();
    }

    public String getMessageAnswer() {
        return messageAnswer;
    }

    public void setMessageAnswer(String messageAnswer) {
        this.messageAnswer = messageAnswer == null ? null : messageAnswer.trim();
    }
}