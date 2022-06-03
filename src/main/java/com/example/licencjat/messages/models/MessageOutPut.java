package com.example.licencjat.messages.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Builder
public class MessageOutPut {
    private String text;
    private UUID senderId;
    private UUID conversationId;
    private Timestamp createdAt;
}
