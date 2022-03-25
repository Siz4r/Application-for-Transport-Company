package com.example.licencjat.authorities;

import com.example.licencjat.authorities.models.AuthorityGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthorityRepository extends JpaRepository<AuthorityGroup, UUID> {
    @PreAuthorize("hasAnyAuthority('A00')")
    Optional<AuthorityGroup> findByCode(String code);
}
