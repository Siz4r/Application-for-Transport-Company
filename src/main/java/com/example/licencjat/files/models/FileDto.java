package com.example.licencjat.files.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FileDto {
    private String cloudinaryId;
    private String fileUrl;
    private String sendToId;
    private String name;
    private LocalDateTime createdAt;
}
