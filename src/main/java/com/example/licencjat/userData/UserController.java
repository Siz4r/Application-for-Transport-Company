package com.example.licencjat.userData;

import com.example.licencjat.UI.Annotations.PreAuthorizeAdmin;
import com.example.licencjat.userData.models.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
//    @PreAuthorizeAdmin
    public List<UserChatDto> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDataDto getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateAnUser(@Valid @RequestBody UserUpdateInput updateInput, @PathVariable("id") UUID id) {
        userService.updateAnUser(UserServiceCommand.builder()
                .updateInput(updateInput).id(id).build());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAnUser(@PathVariable("id") UUID id) {
        userService.deleteAnUser(id);
    }
}
