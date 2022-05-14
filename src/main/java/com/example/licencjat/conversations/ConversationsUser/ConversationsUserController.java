package com.example.licencjat.conversations.ConversationsUser;

import com.example.licencjat.UI.Annotations.PreAuthorizeAny;
import com.example.licencjat.userData.models.UserChatDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/conversations/{convId}/user")
public class ConversationsUserController {
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED, reason = "Resource created successfully")
    @PreAuthorizeAny
    public void addUsersToConv(@RequestBody List<UUID> users, @PathVariable("convId") UUID id) {

    }

//    @PostMapping
//    @ResponseStatus(value = HttpStatus.CREATED, reason = "Resource created successfully")
//    @PreAuthorizeAny
//    public void addUserToConv(@RequestBody UserChatDto user, @PathVariable("convId") UUID id) {
//
//    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Resource deleted successfully")
    @PreAuthorizeAny
    public void deleteUserFromConve(@PathVariable("id") UUID userId, @PathVariable("convId") UUID convId) {

    }
}
