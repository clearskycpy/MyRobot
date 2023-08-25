package cn.clearskycpy.myrobot.common;

/**
 * @description: 枚举信息定义
 * @author：ClearSky
 * @date: 2023/8/25/025
 * @Copyright： https://clearskycpy.cn
 */
public class Constants {
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
}
