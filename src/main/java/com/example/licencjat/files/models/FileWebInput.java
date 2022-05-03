package com.example.licencjat.files.models;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class FileWebInput {
    private String name;
    private MultipartFile file;
}
