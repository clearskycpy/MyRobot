package cn.clearskycpy.myrobot.user;

import cn.clearskycpy.myrobot.common.Constants;
import cn.clearskycpy.myrobot.common.po.User;
import cn.clearskycpy.myrobot.common.support.ids.IIdGenerator;
import cn.clearskycpy.myrobot.mapper.UserMapper;
import cn.clearskycpy.myrobot.service.user.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * @description:
 * @author：clearSky
 * @date: 2023/9/14
 * @Copyright： clearskycpy.cn
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;
    @Resource
    private IUserService userService;

    @Resource
    private Map<Constants.Ids, IIdGenerator> idGeneratorMap;

    /**
     * 插入数据测试
     */
    @Test
    public void insert() {
        User user = new User();
        user.setuId(idGeneratorMap.get(Constants.Ids.SnowFlake).nextId());
        user.setUserType(Constants.UserType.USER_BASIS.getCode());
        user.setState(Constants.UserState.DOING.getCode());
        user.setUserName("MY_TEST");
        user.setPassword("123456");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setMessageCnt(0);
        user.setPhone("12345678909");
        userMapper.insert(user);
    }
}
