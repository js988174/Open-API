package com.project.api.controller;

import com.project.api.vo.MessageVo;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class WebSocketController {

    @MessageMapping("/chat")
    @SendTo("/topic")
    public MessageVo sendMessage(@Payload MessageVo message, SimpMessageHeaderAccessor headerAccessor) {
        System.out.println("왔다 채팅");
        System.out.println(message.getContent());
        headerAccessor.getSessionAttributes().put("username", message.getSender());
        return message;
    }
}