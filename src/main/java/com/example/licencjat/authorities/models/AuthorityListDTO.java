package com.example.licencjat.authorities.models;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor @NoArgsConstructor
public class AuthorityListDTO {
    String name;
    UUID id;
}
