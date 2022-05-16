package com.example.licencjat.chat;

import com.example.licencjat.messages.models.MessageInput;
import com.example.licencjat.messages.models.MessageOutPut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/send/")
    @SendTo("/receive/")
    public MessageOutPut greeting(@Payload MessageInput message) {
        System.out.println(message.getContent());
        return MessageOutPut.builder()
                .convId(message.getConvID())
                .sender(message.getSenderID())
                .text(message.getContent()).build();
    }

    @MessageMapping("/conversationMessage/")
    public MessageOutPut recMessage(@Payload MessageInput message){
        var output = MessageOutPut.builder()
                .convId(message.getConvID())
                .sender(message.getSenderID())
                .text(message.getContent()).build();
        simpMessagingTemplate.convertAndSendToUser(message.getConvID().toString(),"/private",output);
        return output;
    }
}
