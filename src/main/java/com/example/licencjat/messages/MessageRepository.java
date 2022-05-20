package com.example.licencjat.messages;

import com.example.licencjat.messages.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {
    Set<Message> getMessageByConversationId(UUID uuid);
}
