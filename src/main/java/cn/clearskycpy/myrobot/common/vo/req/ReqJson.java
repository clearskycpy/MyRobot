package cn.clearskycpy.myrobot.common.vo.req;

/**
 * @description:  请求对象
 * @author：ClearSky
 * @date: 2023/8/25/025
 * @Copyright： https://clearskycpy.cn
 */

import lombok.Data;

@Data
public class ReqJson {
    private Header header;
    private Parameter parameter;
    private Payload payload;
}
