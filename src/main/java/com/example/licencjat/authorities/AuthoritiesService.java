package com.example.licencjat.authorities;

import com.example.licencjat.authorities.models.AuthorityCommand;
import com.example.licencjat.authorities.models.AuthorityIdDTO;
import com.example.licencjat.authorities.models.AuthorityListDTO;

import java.util.List;

public interface AuthoritiesService {
    AuthorityIdDTO getAuthorityById(String id);
    List<AuthorityListDTO> getAuthorities();
    void addAuthority(AuthorityCommand authorityCommand);
    void deleteAuthority(AuthorityCommand authorityCommand);
    void updateAuthority(AuthorityCommand authorityCommand);
}
