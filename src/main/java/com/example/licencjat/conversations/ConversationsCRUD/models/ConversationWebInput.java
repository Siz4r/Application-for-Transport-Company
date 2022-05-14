package com.example.licencjat.conversations.ConversationsCRUD.models;

import com.example.licencjat.userData.models.UserChatDto;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class ConversationWebInput {
    @NotBlank
    private String name;

    @NotNull
    private List<UserChatDto> users;
}
