package com.example.licencjat.company.models;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class CompanyServiceCommand {
    private final UUID id;
    private final CompanyWebInput webInput;
}
