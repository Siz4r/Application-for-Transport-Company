package com.example.licencjat.user.models;

import com.example.licencjat.user.authorities.models.AuthorityGroup;
import com.example.licencjat.files.models.File;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "UserData")
public class User {
    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String city;
    private String street;
    private String postalCode;
    private Integer buildingNumber;

    @JsonManagedReference
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<File> files = new ArrayList<>();

    @ManyToMany(cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST,
            }, fetch = FetchType.EAGER)
    @JoinTable(name = "user_groups",
            joinColumns =@JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private List<AuthorityGroup> userGroups= new ArrayList<>();



    public void addFile(File file) {
        files.add(file);
        file.setUser(this);
    }
}
