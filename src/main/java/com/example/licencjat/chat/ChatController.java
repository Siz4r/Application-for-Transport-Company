package com.example.licencjat.chat;

import com.example.licencjat.messages.MessageService;
import com.example.licencjat.messages.models.MessageInput;
import com.example.licencjat.messages.models.MessageOutPut;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.sql.Timestamp;

@Controller
@AllArgsConstructor
public class ChatController {
    private SimpMessagingTemplate simpMessagingTemplate;
    private MessageService service;

    @MessageMapping("/conversationMessage/")
    public MessageOutPut recMessage(@Payload MessageInput message){
        service.addMessage(message);
        var output = MessageOutPut.builder()
                .ownerId(message.getOwnerId())
                .content(message.getContent())
                .conversationId(message.getConversationId())
                .createdAt(new Timestamp(System.currentTimeMillis())).build();
        simpMessagingTemplate.convertAndSendToUser(message.getConversationId().toString(),"/private",output);
        return output;
    }
}
