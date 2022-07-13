package com.example.licencjat.messages.models;

import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class MessageOutPut {
    private String content;
    private UUID ownerId;
    private UUID conversationId;
    private Timestamp createdAt;
}
