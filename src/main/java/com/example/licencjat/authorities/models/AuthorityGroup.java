package com.example.licencjat.authorities.models;

import com.example.licencjat.user.models.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter
@Setter
@Table(name = "principle_groups")
public class AuthorityGroup {
    @Id
    private UUID Id;

    @Column(unique = true, nullable = false)
    private String code;
    private String name;

    @ManyToMany(mappedBy = "userGroups")
    private List<User> users;
}
