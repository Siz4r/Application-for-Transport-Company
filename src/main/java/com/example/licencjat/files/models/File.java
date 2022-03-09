package com.example.licencjat.files.models;

import com.example.licencjat.userData.models.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
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
    private String fileUrl;
    private UUID sendToId;
    private String name;
    private LocalDateTime createdAt;

    @JsonBackReference
    @ManyToOne
    private User user;
}
