package com.example.licencjat.authentication;

import com.example.licencjat.UI.MyUserDetailsService;
import com.example.licencjat.authentication.models.AuthenticateRequest;
import com.example.licencjat.authentication.models.AuthenticationDto;
import com.example.licencjat.authentication.models.ExpiredTokenException;
import com.example.licencjat.user.UserService;
import com.example.licencjat.user.models.UserDto;
import com.example.licencjat.user.models.UserServiceCommand;
import com.example.licencjat.user.models.UserWebInput;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService userDetailsService;
    private final JwtUtil jwtTokenUtil;
    private final UserService userService;

    @PostMapping("/login")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<AuthenticationDto> login(@RequestBody @Valid AuthenticateRequest authenticateRequest, HttpServletResponse response) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticateRequest.getUsername(), authenticateRequest.getPassword())
            );
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Error");
        }

        final var userDetails = userDetailsService
                .loadUserByUsername(authenticateRequest.getUsername());
        final String accessToken = jwtTokenUtil.generateToken(userDetails, 1000 * 60 * 15);
        final String refreshToken = jwtTokenUtil.generateToken(userDetails, 1000 * 60 * 60 * 24 * 7);

        UserDto user = userService.getUserByEmail(authenticateRequest.getUsername());

        Cookie cookie = new Cookie("RefreshToken", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        // @TODO: change to production domain
        cookie.setDomain("localhost");
        cookie.setSecure(true);
        response.addCookie(cookie);

        return ResponseEntity.ok(
                AuthenticationDto.builder()
                        .accessToken(accessToken)
                        .user(user)
                        .build()
        );
    }

    @PostMapping("/register")
    @ResponseStatus(value = HttpStatus.CREATED, reason = "User created successfully")
    public void registerAnUser(@Valid @RequestBody UserWebInput webInput) {
        userService.addUser(UserServiceCommand.builder().webInput(webInput).build());
    }


    @PostMapping("/refresh_token")
    public ResponseEntity<AuthenticationDto> refreshtoken(@CookieValue("RefreshToken") String refreshToken) {
        var email = userService.getEmail(UUID.fromString(jwtTokenUtil.extractId(refreshToken)));

        if (!jwtTokenUtil.isTokenExpired(refreshToken)) {
            var accessToken = jwtTokenUtil.generateToken(userDetailsService.loadUserByUsername(email), 1000 * 60 * 15);
            UserDto user = userService.getUserByEmail(email);
            return ResponseEntity.ok(
                    AuthenticationDto.builder()
                            .accessToken(accessToken)
                            .user(user)
                            .build()
            );
        } else {
            throw new ExpiredTokenException("Token has expired!");
        }
    }
}
