package com.example.licencjat.conversations.ConversationsCRUD.models;

import com.example.licencjat.conversations.ConversationsUser.ConversationsUser;
import com.example.licencjat.messages.models.Message;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Conversation {
    @Id
    private UUID id;

    @OneToMany
    private Set<Message> messages = new HashSet<>();

    @ManyToOne
    private ConversationsUser conversationsUsers;

    private String name;
}
