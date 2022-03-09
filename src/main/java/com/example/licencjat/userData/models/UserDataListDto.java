package com.example.licencjat.userData.models;

import com.example.licencjat.files.models.File;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UserDataListDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private List<File> files;
}
