package com.example.licencjat.user.models;

import com.example.licencjat.files.models.File;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserListDto {
    private String id;
    private String firstName;
    private String lastName;
    private List<File> files;
}
