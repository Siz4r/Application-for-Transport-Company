package com.example.licencjat.messages.models;

import com.example.licencjat.conversations.ConversationsCRUD.models.Conversation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Message {
    @Id
    private UUID id;

    @ManyToOne
    private Conversation conversation;

    private UUID ownerId;

    private String content;
}
