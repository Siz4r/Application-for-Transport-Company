package com.example.licencjat.conversations.models;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Getter
public class ConversationWebInput {
    @NotBlank
    private String name;

    @NotNull
    private List<UUID> users;
}
