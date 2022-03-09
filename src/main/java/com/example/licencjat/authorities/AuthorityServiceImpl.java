package com.example.licencjat.authorities;

import com.example.licencjat.UI.idGenerator.IdGenerator;
import com.example.licencjat.authorities.models.AuthorityCommand;
import com.example.licencjat.authorities.models.AuthorityGroup;
import com.example.licencjat.authorities.models.AuthorityIdDTO;
import com.example.licencjat.authorities.models.AuthorityListDTO;
import com.example.licencjat.exceptions.NotFoundExceptions.IncorrectIdInputException;
import com.example.licencjat.exceptions.IllegalArgumentExceptions.IncorrectInputDataException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class AuthorityServiceImpl implements AuthoritiesService {
    private final AuthorityRepository authorityRepository;
    private final ModelMapper modelMapper;
    private final IdGenerator idGenerator;

    @Override
    public AuthorityIdDTO getAuthorityById(UUID id) {
        var authorityGroup = authorityRepository.findById(id)
                .orElseThrow(IncorrectIdInputException::new);
        return modelMapper.map(authorityGroup, AuthorityIdDTO.class);
    }

    @Override
    public List<AuthorityListDTO> getAuthorities() {
        var authorities = authorityRepository.findAll();
        return authorities.stream()
                .map(a -> modelMapper.map(a, AuthorityListDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void addAuthority(AuthorityCommand command) {
        authorityRepository.save(
                AuthorityGroup.builder()
                        .Id(UUID.fromString(idGenerator.generateId()))
                        .code(command.getWebInput().getCode())
                        .name(command.getWebInput().getName()).build());
    }

    @Override
    public void deleteAuthority(AuthorityCommand command) {
        authorityRepository.deleteById(command.getAuthorityId());
    }

    @Override
    public void updateAuthority(AuthorityCommand command) {
        var authority = authorityRepository.findById(command.getAuthorityId())
                .orElseThrow(IncorrectIdInputException::new);

        checkIfCodeIsUnique(command.getWebInput().getCode());

        authority.setName(command.getWebInput().getName());
        authority.setCode(command.getWebInput().getCode());

        authorityRepository.save(authority);
    }

    private void checkIfCodeIsUnique(String code) {
        if (authorityRepository.findAll().stream().anyMatch(a -> a.getCode().equals(code))) {
            throw new IncorrectInputDataException("Wrong authority code!");
        }
    }
}
