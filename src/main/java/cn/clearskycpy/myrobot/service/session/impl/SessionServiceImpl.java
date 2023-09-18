package cn.clearskycpy.myrobot.service.session.impl;

import cn.clearskycpy.myrobot.common.Constants;
import cn.clearskycpy.myrobot.common.po.Session;
import cn.clearskycpy.myrobot.common.support.ids.IIdGenerator;
import cn.clearskycpy.myrobot.mapper.SessionMapper;
import cn.clearskycpy.myrobot.service.session.ISessionService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author：clearSky
 * @date: 2023/9/16
 * @Copyright： clearskycpy.cn
 */
@Service
public class SessionServiceImpl extends ServiceImpl<SessionMapper, Session> implements ISessionService {

    @Resource
    private SessionMapper sessionMapper;

    @Resource
    Map<Constants.Ids, IIdGenerator> idGeneratorMap;

    @Override
    public void insertSession(Session session) {
        session.setCreateTime(new Date());
        session.setUpdateTime(new Date());
        session.setSessionId(idGeneratorMap.get(Constants.Ids.SnowFlake).nextId());
        session.setMessageCnt( 0 );
        sessionMapper.insert(session);
    }

    @Override
    public Session autoincrementMessageCnt(Long sessionId, Long uId) {
        LambdaUpdateWrapper<Session> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Session::getSessionId,sessionId).eq(Session::getuId, uId);
        Session session = sessionMapper.selectOne(updateWrapper);
        session.setMessageCnt(session.getMessageCnt() + 1);
        session.setUpdateTime(new Date());
        sessionMapper.update(session,updateWrapper);
        return session;
    }

    @Override
    public List<Session> querySessionListByUId(Long uId) {
        LambdaQueryWrapper<Session> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Session::getuId, uId);
        return sessionMapper.selectList(queryWrapper);
    }


}
