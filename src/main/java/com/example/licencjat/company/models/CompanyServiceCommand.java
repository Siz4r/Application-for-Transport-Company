package com.example.licencjat.company.models;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CompanyServiceCommand {
    private final String id;
    private final CompanyWebInput webInput;
}
