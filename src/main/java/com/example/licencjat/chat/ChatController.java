package com.example.licencjat.chat;

import com.example.licencjat.messages.models.MessageInput;
import com.example.licencjat.messages.models.MessageOutPut;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.UUID;

@Controller
public class ChatController {

    @MessageMapping("/chat")
    @SendTo("/topic/{conversationId}")
    public MessageOutPut greeting(@RequestBody @Valid MessageInput webInput, @PathVariable("conversationId") UUID id) {
        var outPut = new MessageOutPut();
        outPut.setContent("Hello world!");
        return outPut;
    }
}
