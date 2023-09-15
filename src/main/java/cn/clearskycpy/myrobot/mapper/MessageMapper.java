package cn.clearskycpy.myrobot.mapper;

import cn.clearskycpy.myrobot.common.po.Message;
import cn.clearskycpy.myrobot.common.po.Message;
//import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author clearsky
 */
@Mapper
public interface MessageMapper extends BaseMapper<Message> {

    int deleteByPrimaryKey(Long id);

    Message selectByPrimaryKey(Long id);

    List<Message> selectAll();

    int updateByPrimaryKey(Message record);
}