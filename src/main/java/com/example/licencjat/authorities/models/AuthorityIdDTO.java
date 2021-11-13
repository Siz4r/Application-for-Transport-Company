package com.example.licencjat.authorities.models;

import com.example.licencjat.user.models.UserListDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@ToString
@Getter
@Setter
public class AuthorityIdDTO {
    private String code;
    private String name;
    private Set<UserListDto> users;
}
