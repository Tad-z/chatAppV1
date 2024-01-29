package com.tade.chat.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    
    // What is the url you want to be called to run sendMessage
    @MessageMapping("/chat.sendMessage")
    // to what topic or queue you want to send the message to
    @SendTo("/topic/public")
    // 2 methods will be created to add a new user 
    // and to send a message
    public ChatMessage sendMessage(
        @Payload ChatMessage chatMessage
    ) {
        return chatMessage;
    }
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser (
        @Payload ChatMessage chatMessage,
        SimpMessageHeaderAccessor headerAccessor
    ) {
        // Adds username in websocket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}
