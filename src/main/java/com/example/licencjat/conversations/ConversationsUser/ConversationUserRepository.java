package com.example.licencjat.conversations.ConversationsUser;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;
import java.util.UUID;

public interface ConversationUserRepository extends JpaRepository<ConversationsUser, UUID> {
    Set<ConversationsUser> getAllByConversation_Id(UUID id);
}
