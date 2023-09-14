package cn.clearskycpy.myrobot.mapper;

import cn.clearskycpy.myrobot.common.po.Message;
import cn.clearskycpy.myrobot.common.po.Message;
import java.util.List;

public interface MessageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Message record);

    Message selectByPrimaryKey(Long id);

    List<Message> selectAll();

    int updateByPrimaryKey(Message record);
}