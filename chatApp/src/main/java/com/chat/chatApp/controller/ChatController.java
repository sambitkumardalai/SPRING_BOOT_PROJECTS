package com.chat.chatApp.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.chat.chatApp.model.ChatMessage;

@Controller
public class ChatController {
    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(ChatMessage message) {
        // Here you can process the message, e.g., save it to a database
        return message; // Return the message to be sent to subscribers
    }

    @GetMapping("chat")
    public String chat() {
        return "chat";
    }

}
