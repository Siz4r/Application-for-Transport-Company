package com.example.licencjat.files.models;

import com.example.licencjat.user.models.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class File {
    @Id
    private UUID fileId;
    private String cloudinaryId;
    private String url;
    private String senderFirstName;
    private String senderLastName;
    private String name;
    private Timestamp createdAt;

    @JsonBackReference
    @ManyToOne
    private User user;
}
