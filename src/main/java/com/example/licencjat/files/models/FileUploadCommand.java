package com.example.licencjat.files.models;

import lombok.Builder;
import lombok.Getter;

import java.io.File;
import java.util.UUID;

@Getter
@Builder
public class FileUploadCommand {
    private final byte[] bytes;
    private final String name;
    private final UUID sendToId;
}
