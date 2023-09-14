package cn.clearskycpy.myrobot.mapper;

import cn.clearskycpy.myrobot.common.po.Session;
import java.util.List;

public interface SessionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Session record);

    Session selectByPrimaryKey(Long id);

    List<Session> selectAll();

    int updateByPrimaryKey(Session record);
}