package com.example.licencjat.authorities.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthorityCommand {
    private final AuthorityWebInput webInput;
    private final String authorityId;
}
