package com.example.licencjat.conversations.ConversationsCRUD;


import com.example.licencjat.UI.Annotations.PreAuthorizeAny;
import com.example.licencjat.conversations.ConversationsCRUD.models.ConversationListDTO;
import com.example.licencjat.conversations.ConversationsCRUD.models.ConversationWebInput;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/conversations/")
public class ConversationsController {
    private final ConversationsService service;

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

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED, reason = "Resource created successfully")
    @PreAuthorizeAny
    public UUID createConv(@Valid @RequestBody ConversationWebInput webInput) {
        return service.createConversation(webInput);
    }
}
