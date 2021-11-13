package com.example.licencjat.files;

import com.example.licencjat.files.models.File;
import com.example.licencjat.files.models.FileDto;
import com.example.licencjat.files.models.FileListDto;
import com.example.licencjat.files.models.FileUploadCommand;

import java.io.IOException;
import java.util.List;

interface FileService {
    void updateAnImage(FileUploadCommand command) throws IOException;
    void deleteAnImage(String id) throws Exception;
    List<FileListDto> getFiles();
    FileDto getFileById(String id);
}
