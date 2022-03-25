package com.example.licencjat.authorities;

import com.example.licencjat.authorities.models.AuthorityCommand;
import com.example.licencjat.authorities.models.AuthorityIdDTO;
import com.example.licencjat.authorities.models.AuthorityListDTO;
import com.example.licencjat.authorities.models.AuthorityWebInput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/authorities/")
public class AuthoritiesController {
    private final AuthoritiesService authoritiesService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED, reason = "Resource created successfully")
    @PreAuthorize("hasAuthority('A00')")
    public void addAuthority(@Valid @RequestBody AuthorityWebInput webInput) {
        authoritiesService.addAuthority(AuthorityCommand.builder()
                .webInput(webInput).build());
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('A00')")
    public void editAuthority(@Valid @RequestBody AuthorityWebInput webInput,
                              @PathVariable("id") UUID id) {
        authoritiesService.updateAuthority(AuthorityCommand.builder()
                .webInput(webInput)
                .authorityId(id).build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Resource deleted successfully")
    @PreAuthorize("hasAuthority('A00')")
    public void deleteAuthority(@PathVariable("id") UUID id) {
        authoritiesService.deleteAuthority(AuthorityCommand.builder()
                .authorityId(id).build());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('A00')")
    public AuthorityIdDTO getAuthority(@PathVariable("id") UUID id) {
        return authoritiesService.getAuthorityById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('A00')")
    public List<AuthorityListDTO> getAuthorities() {
        return authoritiesService.getAuthorities();
    }
}
