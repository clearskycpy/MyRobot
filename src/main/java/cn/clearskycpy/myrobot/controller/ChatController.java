package cn.clearskycpy.myrobot.controller;

import cn.clearskycpy.myrobot.common.Result;
import cn.clearskycpy.myrobot.common.dto.ChatDto;
import cn.clearskycpy.myrobot.common.myexception.ChatException;
import cn.clearskycpy.myrobot.service.chat.IChatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description: 聊天业务接口
 * @author：clearSky
 * @date: 2023/9/19
 * @Copyright： clearskycpy.cn
 */
@RestController
@RequestMapping("/chat")
@Api(tags = "聊天业务接口")
public class ChatController {

    @Resource
    private IChatService chatService;

    @ApiOperation(value = "提问", notes = "发起一个问题")
    @PostMapping("/askQuestion")
    public Result askQuestion(ChatDto chatDto) throws Exception {
        String answer = chatService.sendMessage(chatDto);
        return Result.success(answer);
    }
}
