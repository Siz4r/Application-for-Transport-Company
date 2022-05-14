package com.example.licencjat.messages.models;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class MessageOutPut {
    private String content;
    private UUID ownerId;
}
