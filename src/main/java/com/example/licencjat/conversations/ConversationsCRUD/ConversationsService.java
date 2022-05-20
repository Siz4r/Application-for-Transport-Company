package com.example.licencjat.conversations.ConversationsCRUD;

import com.example.licencjat.conversations.ConversationsCRUD.models.ConversationListDTO;
import com.example.licencjat.conversations.ConversationsCRUD.models.ConversationWebInput;

import java.util.List;
import java.util.UUID;

public interface ConversationsService {
    List<ConversationListDTO> getConversations();
    void deleteConversation(UUID id);
    ConversationListDTO createConversation(ConversationWebInput webInput);
}
