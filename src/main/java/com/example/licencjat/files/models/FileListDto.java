package com.example.licencjat.files.models;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class FileListDto {
    private UUID fileId;
    private String fileUrl;
    private String names;
}
