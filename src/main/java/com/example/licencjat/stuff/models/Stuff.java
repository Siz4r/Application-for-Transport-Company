package com.example.licencjat.stuff.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Stuff {
    @Id
    private String Id;
    private Integer quantity;
    private String name;
    private int prize;
}
