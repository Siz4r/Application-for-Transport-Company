package com.example.licencjat.user.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "UserData")
public class User {
    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
}
