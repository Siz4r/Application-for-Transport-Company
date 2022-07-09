package com.example.licencjat.conversations.ConversationsCRUD;


import com.example.licencjat.UI.Annotations.PreAuthorizeAny;
import com.example.licencjat.conversations.ConversationsCRUD.models.ConversationListDTO;
import com.example.licencjat.conversations.ConversationsCRUD.models.ConversationWebInput;
import com.example.licencjat.security.AuthenticationFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/conversations/")
public class ConversationsController {
    private final ConversationsService service;
    private SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorizeAny
    public List<ConversationListDTO> getConversations() {
        return service.getConversations();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Resource deleted successfully")
    @PreAuthorizeAny
    public void deleteConv(@PathVariable("id") UUID id) {
        service.deleteConversation(id);
    }

    @PreAuthorizeAny
    @MessageMapping("/conversations/")
    @PostMapping
    public ConversationListDTO createConv(@Valid @RequestBody ConversationWebInput webInput) {
        var conv = service.createConversation(webInput);

        for (var u :
                conv.getUsers()) {
                   simpMessagingTemplate.convertAndSendToUser(u.getId().toString(),"/new",conv);
        }

        return conv;
    }
}
