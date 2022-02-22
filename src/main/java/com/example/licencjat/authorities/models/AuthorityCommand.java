package com.example.licencjat.authorities.models;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class AuthorityCommand {
    private final AuthorityWebInput webInput;
    private final UUID authorityId;
}
