package com.example.licencjat.company.models;

import com.example.licencjat.files.models.File;
import com.example.licencjat.stuff.models.Stuff;
import com.example.licencjat.user.models.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Company {
    @Id
    private String id;
    private String name;
    private String address;

    @JsonManagedReference
    @OneToMany(
            mappedBy = "company",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Stuff> stuffList = new ArrayList<>();

    public void addStuff(Stuff stuff) {
        stuffList.add(stuff);
        stuff.setCompany(this);
    }
}
