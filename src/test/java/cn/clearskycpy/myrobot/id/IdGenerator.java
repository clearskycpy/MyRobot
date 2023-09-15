package cn.clearskycpy.myrobot.id;

import cn.clearskycpy.myrobot.common.Constants;
import cn.clearskycpy.myrobot.common.support.ids.IIdGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description:
 * @author：clearSky
 * @date: 2023/9/14
 * @Copyright： clearskycpy.cn
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IdGenerator {

    private Logger logger = LoggerFactory.getLogger(IdGenerator.class);

    @Resource
    private Map<Constants.Ids, IIdGenerator> idGeneratorMap;

    /**
     * 测试id生成规则
     */
    @Test
    public void TestNextId() {
        logger.info("随机生成的方式生成id ： {}", idGeneratorMap.get(Constants.Ids.RandomNumeric).nextId());
        logger.info("日期算法的方式生成id ： {}", idGeneratorMap.get(Constants.Ids.ShortCode).nextId());
        logger.info("雪花算法的方式生成id ： {}", idGeneratorMap.get(Constants.Ids.SnowFlake).nextId());

    }
}
