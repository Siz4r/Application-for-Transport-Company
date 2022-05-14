package com.example.licencjat.conversations.ConversationsUser;

import com.example.licencjat.conversations.ConversationsCRUD.models.Conversation;
import com.example.licencjat.userData.models.User;
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
public class ConversationsUser {
    @Id
    private UUID id;

    @ManyToOne
    private Conversation conversations;

    @ManyToOne
    private User user;
}
