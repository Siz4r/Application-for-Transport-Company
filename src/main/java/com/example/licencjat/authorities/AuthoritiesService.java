package com.example.licencjat.authorities;

import com.example.licencjat.authorities.models.AuthorityCommand;
import com.example.licencjat.authorities.models.AuthorityIdDTO;
import com.example.licencjat.authorities.models.AuthorityListDTO;

import java.util.List;
import java.util.UUID;

public interface AuthoritiesService {
    AuthorityIdDTO getAuthorityById(UUID id);
    List<AuthorityListDTO> getAuthorities();
    void addAuthority(AuthorityCommand authorityCommand);
    void deleteAuthority(AuthorityCommand authorityCommand);
    void updateAuthority(AuthorityCommand authorityCommand);
}
