package cn.clearskycpy.myrobot.service.session;

import cn.clearskycpy.myrobot.common.po.Session;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @description:
 * @author：clearSky
 * @date: 2023/9/16
 * @Copyright： clearskycpy.cn
 */
public interface ISessionService extends IService<Session> {

    /**
     * 插入一个session
     * @param session  会话实体类  包括sessionUId sessionName
     */
    void insertSession(Session session);

    /**
     * 对 当前会话进行记录数加一
     * @param sessionId  会话id
     * @param uId  用户id
     * @return  session 实体类
     */
    Session autoincrementMessageCnt(Long sessionId, Long uId);

    /**
     * 获取所有的会话根据 uId获取
     * @param uId   所属用户id
     * @return 会话列表
     */
    List<Session> querySessionListByUId(Long uId);



}
