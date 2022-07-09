package com.example.licencjat.files;

import com.example.licencjat.exceptions.NotFoundExceptions.CloudinaryException;
import com.example.licencjat.files.models.FileDto;
import com.example.licencjat.files.models.FileListDto;
import com.example.licencjat.files.models.FileUploadCommand;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM_VALUE;

@AllArgsConstructor
@RequestMapping("/api/files")
@RestController
public class FileController {
    private final FileService service;

    @PostMapping(value = "/{sendToId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addFile(@RequestParam("file") MultipartFile file,
                        @PathVariable("sendToId") UUID sendToId) {
        try {
            service.updateAnImage(FileUploadCommand.builder()
                    .sendToId(sendToId)
                    .name(file.getOriginalFilename())
                    .bytes(file.getBytes()).build());
        } catch (Exception e) {
            throw new CloudinaryException(e.getMessage());
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FileListDto> getFiles() {
        return service.getFiles();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteFile(@PathVariable("id") UUID id) throws Exception {
        service.deleteAnImage(id);
    }
}
