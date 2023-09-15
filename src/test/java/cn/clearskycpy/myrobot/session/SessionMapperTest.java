package cn.clearskycpy.myrobot.session;

import cn.clearskycpy.myrobot.common.po.Session;
import cn.clearskycpy.myrobot.mapper.SessionMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author：clearSky
 * @date: 2023/9/14
 * @Copyright： clearskycpy.cn
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SessionMapperTest {
    @Resource
    private SessionMapper sessionMapper;
    @Test
    public void load() {
//        sessionMapper.insert(new Session());
        Session session = sessionMapper.selectById(11111);
        List<Session> sessions = sessionMapper.selectAll();
    }
}
