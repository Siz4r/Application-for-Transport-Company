package com.example.licencjat.conversations.ConversationsUser;

import com.example.licencjat.conversations.ConversationsCRUD.models.Conversation;
import com.example.licencjat.user.models.User;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class ConversationsUser {
    @Id
    private UUID id;

    @ManyToOne
    private Conversation conversation;

    @ManyToOne
    private User user;
}
