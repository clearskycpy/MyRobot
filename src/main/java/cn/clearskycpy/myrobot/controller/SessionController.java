package cn.clearskycpy.myrobot.controller;

import cn.clearskycpy.myrobot.common.Result;
import cn.clearskycpy.myrobot.common.po.Message;
import cn.clearskycpy.myrobot.common.po.Session;
import cn.clearskycpy.myrobot.service.message.IMessageService;
import cn.clearskycpy.myrobot.service.session.ISessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:  会话接口
 * @author：clearSky
 * @date: 2023/9/19
 * @Copyright： clearskycpy.cn
 */
@RestController
@RequestMapping("/chat/session")
@Api(tags = "会话接口")
public class SessionController {

    @Resource
    private ISessionService sessionService;

    @Resource
    private IMessageService messageService;

    /**
     * 根据用户id 获取所有会话
     * @param uId  用户id
     * @return
     */
    @ApiOperation(value = "获取会话列表", notes = "获取当前用户列表")
    @GetMapping("/getSessionList/{uId}")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name="uId",value = "用户id",required = true)
    })
    public Result getSessionList (@PathVariable Long uId) {
        List<Session> sessions = sessionService.querySessionListByUId(uId);
        return Result.success(sessions);
    }

    @ApiOperation(value = "获取当前会话的历史消息", notes = "根据用户id和会话id，获取当前会话的历史信息")
    @GetMapping("/getDetailSession/{sessionId}/{uId}")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name="sessionId",value = "会话id",required = true),
            @ApiImplicitParam(paramType = "query",name="uId",value = "用户id",required = true)
    })
    public Result getDetailSession(@PathVariable Long sessionId, @PathVariable Long uId) {
        List<Message> messageList = messageService.getMessageListBySessionAndUId(uId, sessionId);
        return Result.success(messageList);
    }

    @ApiOperation(value = "创建一个新的会话", notes = "创建一个新的会话")
    @PostMapping("/createSession")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name="uId",value = "用户id",required = true),
            @ApiImplicitParam(paramType = "query",name="sessionName",value = "会话名称",required = true)
    })
    public Result createSession(Long uId, String sessionName) {
        Session session = new Session();
        session.setuId(uId);
        session.setSessionName(sessionName);
        sessionService.insertSession(session);
        return Result.success();
    }


}
