package com.example.licencjat.files;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.licencjat.UI.idGenerator.IdGenerator;
import com.example.licencjat.exceptions.IncorrectIdInputException;
import com.example.licencjat.files.models.File;
import com.example.licencjat.files.models.FileDto;
import com.example.licencjat.files.models.FileListDto;
import com.example.licencjat.files.models.FileUploadCommand;
import com.example.licencjat.user.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService{
    private final FileRepository fileRepository;
    private final UserRepository userRepository;
    private final IdGenerator idGenerator;
    private final ModelMapper mapper;
    private final Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "siz4rimag",
            "api_key", "551523122998615",
            "api_secret", "XOw2VK3j0A75TEGbZf1Z8ABjK4k"));

    @Override
    public void updateAnImage(FileUploadCommand command) throws IOException {
        var user = userRepository.findById(command.getUserId()).orElseThrow(() -> new IncorrectIdInputException(""));

        var options = ObjectUtils.asMap("resource_type", "auto",
        "format", "pdf");

        Map result = cloudinary.uploader().upload(command.getBytes(), options);

        var date = LocalDateTime.now();

        user.addFile(File.builder()
                .fileId(UUID.fromString(idGenerator.generateId()))
                .cloudinaryId(result.get("public_id").toString())
                .createdAt(date)
                .fileUrl((String) result.get("secure_url"))
                .name(command.getName())
                .sendToId(command.getSendToId()).build());

        userRepository.save(user);
    }

    @Override
    public void deleteAnImage(UUID id) throws Exception {
        var file = fileRepository.findById(id).orElseThrow(() -> new IncorrectIdInputException(""));

        cloudinary.api().deleteResources(Collections.singletonList(file.getCloudinaryId()), ObjectUtils.emptyMap());

        fileRepository.deleteById(id);
    }

    @Override
    public List<FileListDto> getFiles() {
        var files = fileRepository.findAll();

        return files.stream()
                .map(f -> mapper.map(f, FileListDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public FileDto getFileById(UUID id) {
        var file = fileRepository.findById(id).orElseThrow(() -> new IncorrectIdInputException(""));

        return mapper.map(file, FileDto.class);
    }
}
