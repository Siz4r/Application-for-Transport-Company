package com.example.licencjat.messages.models;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
public class MessageInput {
    @NotNull
    private UUID convID;

    @NotNull
    private UUID senderID;

    @NotBlank
    private String content;
}
