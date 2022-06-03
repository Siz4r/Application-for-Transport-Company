package com.example.licencjat.conversations.ConversationsCRUD.models;

import com.example.licencjat.conversations.ConversationsUser.ConversationsUser;
import com.example.licencjat.messages.models.Message;
import lombok.*;

import javax.persistence.*;
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

    @OneToMany(
            fetch = FetchType.EAGER
    )
    private Set<Message> messages = new HashSet<>();

    @OneToMany(
            fetch = FetchType.LAZY
    )
    private Set<ConversationsUser> conversationsUsers;

    private String name;
}
