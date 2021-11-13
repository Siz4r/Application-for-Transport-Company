package com.example.licencjat.files.models;

import com.example.licencjat.user.models.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class File {
    @Id
    private String fileId;
    private String cloudinaryId;
    private String fileUrl;
    private String sendToId;
    private String name;
    private LocalDateTime createdAt;

    @JsonBackReference
    @ManyToOne
    private User user;
}
