package cn.clearskycpy.myrobot.common;

/**
 * @description: 枚举信息定义
 * @author：ClearSky
 * @date: 2023/8/25/025
 * @Copyright： https://clearskycpy.cn
 */
public class Constants {
    /**
     * 可使用消息数默认值
     */
    public final static Integer MESSAGE_DEFAULT_VALUE = 100;


    public final static String LOGIN = "chat_user";

    /**
     * 响应状态码
     */
    public enum ResponseCode {
        /**
         * 成功
         */
        SUCCESS("200", "成功"),
        /**
         * 失败
         */
        UN_ERROR("500", "未知失败"),

        /**
         * 验证码错误
         */
        CODE_ERROR("0001","验证码输入错误"),

        ILLEGAL_PARAMETER("0002", "非法参数"),
        INDEX_DUP("0003", "主键冲突"),
        NO_UPDATE("0004","SQL操作无更新");

        private String code;
        private String info;

        ResponseCode(String code, String info) {
            this.code = code;
            this.info = info;
        }

        public String getCode() {
            return code;
        }

        public String getInfo() {
            return info;
        }

    }

    public enum WriteState {
//        writing
        WRITING(false, "写入中"),
//      Written
        WRITTEN(true, "已写完");

        private boolean writeState;
        private String msg;

        WriteState(boolean writeState, String msg) {
            this.writeState = writeState;
            this.msg = msg;
        }

        public boolean getWriteState() {
            return writeState;
        }
        public String getMsg() {
            return msg;
        }
    }
    /**
     * Ids 生成策略枚举
     */
    public enum Ids {
        /**
         * 雪花算法
         */
        SnowFlake,
        /**
         * 日期算法
         */
        ShortCode,
        /**
         * 随机算法
         */
        RandomNumeric;
    }

    /**
     * 账号状态 1 可用 2 禁用 3 删除
     */
    public enum UserState {

        /**
         * 可用
         */
        DOING(1,"可用"),

        /**
         * 禁用
         */
        DISABLE(2,"禁用"),

        /**
         * 已删除
         */
        DELETED(3,"删除");

        private Integer code;

        private String info;

        UserState(Integer code, String info) {
            this.code = code;
            this.info = info;
        }


        public Integer getCode() {
            return code;
        }

        public String getInfo() {
            return info;
        }
    }

    /**
     * 用户类型 1 普通用户 2  会员用户 3 管理员用户 4 root用户',
     */
    public enum UserType {
        /**
         * 普通用户 基础
         */
        USER_BASIS(1,"普通用户"),

        /**
         * 会员用户
         */
        USER_VIP(2,"会员用户"),

        /**
         * 管理员用户
         */
        USER_ADMIN(2,"管理员用户"),

        /**
         * Root用户 系统后门
         */
        USER_CLEAR_SKY(4,"MyRoot");

        private Integer code;
        private String info;

        UserType(Integer code, String info) {
            this.code = code;
            this.info = info;
        }

        public Integer getCode() {
            return code;
        }

        public String getInfo() {
            return info;
        }
    }

    public enum TextRole {
        /**
         * 提问头
         */
        USER("user", "提问"),

        /**
         * 回答头
         */
        ASSISTANT("assistant", "回答")
        ;
        private String code;
        private String info;

        TextRole(String code, String info) {
            this.code = code;
            this.info = info;
        }

        public String getCode() {
            return code;
        }

        public String getInfo() {
            return info;
        }
    }

    public enum ChatParameter {

        /**
         * chat 默认参数
         */
        DEFAULT("general", 0.5, 1024),

        /**
         * chat v2 参数
         */
        V2("generalv2", 0.5, 1024);

        private String domain;
        private Double temperature;
        private Integer max_tokens;

        ChatParameter(String domain, Double temperature, Integer max_tokens) {
            this.domain = domain;
            this.temperature = temperature;
            this.max_tokens = max_tokens;
        }

        public String getDomain() {
            return domain;
        }

        public Double getTemperature() {
            return temperature;
        }

        public Integer getMax_tokens() {
            return max_tokens;
        }
    }

    public enum ChatExceptionType {
        /**
         *  未能得到消息
         */
        RESPONSE_FAILED(1,"api 响应失败"),
        ;
        /**
         * 响应状态码
         */
        private Integer code;

        /**
         * 失败原因
         */
        private String message;

        public Integer getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        ChatExceptionType(Integer code, String message) {
            this.code = code;
            this.message = message;
        }
    }
}
