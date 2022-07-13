package com.example.licencjat.conversations;

import com.example.licencjat.conversations.models.ConversationListDTO;
import com.example.licencjat.conversations.models.ConversationWebInput;

import java.util.List;
import java.util.UUID;

public interface ConversationsService {
    List<ConversationListDTO> getConversations();
    void deleteConversation(UUID id);
    ConversationListDTO createConversation(ConversationWebInput webInput);
}
