package com.example.licencjat.conversations.ConversationsCRUD.models;

import com.example.licencjat.messages.models.MessageOutPut;
import com.example.licencjat.user.models.UserChatDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class ConversationListDTO {
    private UUID conversationId;

    private String conversationName;

    private Set<UserChatDto> users;

    private Set<MessageOutPut> messages;
}
