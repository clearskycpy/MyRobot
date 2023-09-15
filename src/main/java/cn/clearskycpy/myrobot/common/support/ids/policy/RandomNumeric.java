package cn.clearskycpy.myrobot.common.support.ids.policy;


import cn.clearskycpy.myrobot.common.support.ids.IIdGenerator;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

/**
 * @description: 工具类生成 org.apache.commons.lang3.RandomStringUtils
 */
@Component
public class RandomNumeric implements IIdGenerator {

    @Override
    public long nextId() {
        return Long.parseLong(RandomStringUtils.randomNumeric(11));
    }

}
