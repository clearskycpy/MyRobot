package cn.clearskycpy.myrobot.mapper;

import cn.clearskycpy.myrobot.common.po.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author clearSky
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}