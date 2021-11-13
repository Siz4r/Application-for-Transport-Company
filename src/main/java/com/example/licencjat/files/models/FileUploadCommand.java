package com.example.licencjat.files.models;

import lombok.Builder;
import lombok.Getter;

import java.io.File;

@Getter
@Builder
public class FileUploadCommand {
    private final byte[] bytes;
    private final String name;
    private final String sendToId;
    private final String userId;
}
