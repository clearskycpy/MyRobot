package cn.clearskycpy.myrobot.mapper;

import cn.clearskycpy.myrobot.common.po.Session;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SessionMapper extends BaseMapper<Session> {
    int deleteByPrimaryKey(Long id);

    Session selectByPrimaryKey(Long id);

    List<Session> selectAll();

    int updateByPrimaryKey(Session record);
}