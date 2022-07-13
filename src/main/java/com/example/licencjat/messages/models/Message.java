package com.example.licencjat.messages.models;

import com.example.licencjat.conversations.models.Conversation;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class Message {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "conversation_id", nullable = false)
    private Conversation conversation;

    private UUID ownerId;

    private String content;

    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

}
