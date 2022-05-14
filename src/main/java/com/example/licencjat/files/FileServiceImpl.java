package com.example.licencjat.files;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.licencjat.UI.idGenerator.IdGenerator;
import com.example.licencjat.exceptions.NotFoundExceptions.CloudinaryException;
import com.example.licencjat.exceptions.NotFoundExceptions.IncorrectIdInputException;
import com.example.licencjat.files.models.File;
import com.example.licencjat.files.models.FileDto;
import com.example.licencjat.files.models.FileListDto;
import com.example.licencjat.files.models.FileUploadCommand;
import com.example.licencjat.security.AuthenticationFacade;
import com.example.licencjat.userData.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService{
    private final FileRepository fileRepository;
    private final UserRepository userRepository;
    private final AuthenticationFacade authenticationFacade;
    private final IdGenerator idGenerator;
    private final ModelMapper mapper;
    private final Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "siz4rimag",
            "api_key", "551523122998615",
            "api_secret", "XOw2VK3j0A75TEGbZf1Z8ABjK4k"));

    @Override
    public void updateAnImage(FileUploadCommand command) {
        var receiver = userRepository.findById(command.getSendToId()).orElseThrow(IncorrectIdInputException::new);
        var sender = userRepository.findById(command.getFromId()).orElseThrow(IncorrectIdInputException::new);

        var fileName = command.getName();

        var options = ObjectUtils.asMap("resource_type", "auto",
        "format", fileName.substring(fileName.lastIndexOf(".")));

        try {
            var result = cloudinary.uploader().upload(command.getBytes(), options);

            receiver.addFile(File.builder()
                    .fileId(UUID.fromString(idGenerator.generateId()))
                    .cloudinaryId(result.get("public_id").toString())
                    .createdAt(new Timestamp(System.currentTimeMillis()))
                    .url((String) result.get("secure_url"))
                    .name(command.getName())
                    .senderFirstName(sender.getFirstName())
                    .senderLastName(sender.getLastName()).build());
        } catch (IOException e) {
            throw new CloudinaryException(e.getMessage());
        }

        userRepository.save(receiver);
    }

    @Override
    public void deleteAnImage(UUID id) throws Exception {
        var file = fileRepository.findById(id).orElseThrow(IncorrectIdInputException::new);

        cloudinary.api().deleteResources(Collections.singletonList(file.getCloudinaryId()), ObjectUtils.emptyMap());

        fileRepository.deleteById(id);
    }

    @Override
    public List<FileListDto> getFiles() {
        var userId = authenticationFacade.getCurrentAuthenticatedUser().getId();
        var owner = userRepository.findById(userId).orElseThrow(IncorrectIdInputException::new);

        return owner.getFiles().stream()
                .map(f -> mapper.map(f, FileListDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public FileDto getFileById(UUID id) {
        var file = fileRepository.findById(id).orElseThrow(IncorrectIdInputException::new);

        return mapper.map(file, FileDto.class);
    }
}
