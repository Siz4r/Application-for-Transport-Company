package com.example.licencjat.authorities.models;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
public class AuthorityWebInput {
    @NotBlank
    private final String name;
    @NotBlank
    private final String code;
}
