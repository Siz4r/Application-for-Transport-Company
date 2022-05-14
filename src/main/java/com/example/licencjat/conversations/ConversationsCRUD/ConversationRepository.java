package com.example.licencjat.conversations.ConversationsCRUD;

import com.example.licencjat.conversations.ConversationsCRUD.models.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, UUID> {
}
