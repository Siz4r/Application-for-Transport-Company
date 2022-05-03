package com.example.licencjat.files.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
public class FileListDto {
    private UUID id;
    private String url;
    private String name;
    private String senderFirstName;
    private String senderLastName;
    private Timestamp createdAt;
}
