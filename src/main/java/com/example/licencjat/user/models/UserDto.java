package com.example.licencjat.user.models;

import com.example.licencjat.files.models.File;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    //Moze nie być dobrym pomysłem zwracanie hasła
    // w dto ale na poczatek projektu zostanie
    private String password;
    private String phoneNumber;
    private List<File> files;
}
