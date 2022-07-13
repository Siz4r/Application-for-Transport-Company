package com.example.licencjat.conversations.models;

import com.example.licencjat.messages.models.Message;
import com.example.licencjat.user.models.User;
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
            fetch = FetchType.EAGER,
            mappedBy = "conversation",
            cascade = CascadeType.ALL
    )
    private Set<Message> messages = new HashSet<>();

    @ManyToMany(
            fetch = FetchType.LAZY,
            mappedBy = "conversations",
            cascade = CascadeType.ALL
    )
    private Set<User> users = new HashSet<>();

    private String name;
}
