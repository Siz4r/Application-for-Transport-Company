package com.example.licencjat.user;

import com.example.licencjat.user.models.UserListDto;
import com.example.licencjat.user.models.UserServiceCommand;
import com.example.licencjat.user.models.UserUpdateInput;
import com.example.licencjat.user.models.UserWebInput;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/register")
public class UserController {
    private final UserService userService;


//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public void registerAnUser(@Valid @RequestBody UserWebInput webInput) {
//        userService.addUser(UserServiceCommand.builder()
//                .webInput(webInput).build());
//    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserListDto> getUsers() {
        return userService.getUsers();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateAnUser(@Valid @RequestBody UserUpdateInput updateInput, @PathVariable("id") String id) {
        userService.updateAnUser(UserServiceCommand.builder()
                .updateInput(updateInput).id(id).build());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAnUser(@PathVariable("id") String id) {
        userService.deleteAnUser(id);
    }
}
