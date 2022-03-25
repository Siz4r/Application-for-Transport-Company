package com.example.licencjat.authentication;

import com.example.licencjat.authentication.models.AuthenticateRequest;
import com.example.licencjat.authentication.models.AuthenticationDto;
import com.example.licencjat.userData.UserService;
import com.example.licencjat.userData.models.UserServiceCommand;
import com.example.licencjat.userData.models.UserWebInput;
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
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtTokenUtil;
    private final UserService userService;

    @PostMapping("/login")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<AuthenticationDto> login(@RequestBody @Valid AuthenticateRequest authenticateRequest, HttpServletResponse response) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticateRequest.getUsername(), authenticateRequest.getPassword())
            );
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Error occurred while authenticating user!");
        }

        final var userDetails = userService
                .loadUserByUsername(authenticateRequest.getUsername());

        addCookieToResponse(response, jwtTokenUtil.generateToken(userDetails, 1000 * 60 * 60 * 24 * 7));

        return ResponseEntity.ok(
                AuthenticationDto.builder()
                        .accessToken(jwtTokenUtil.generateToken(userDetails, 1000 * 60 * 15))
                        .user(userService.getUserByEmail(authenticateRequest.getUsername()))
                        .build()
        );
    }



    @PostMapping("/refresh_token")
    public ResponseEntity<AuthenticationDto> refreshtoken(@CookieValue("RefreshToken") String refreshToken) {
        var email = userService.getEmail(UUID.fromString(jwtTokenUtil.extractId(refreshToken)));

        if (!jwtTokenUtil.isTokenExpired(refreshToken)) {
            return ResponseEntity.ok(
                    AuthenticationDto.builder()
                            .accessToken(jwtTokenUtil.generateToken(userService.loadUserByUsername(email), 1000 * 60 * 15))
                            .user(userService.getUserByEmail(email))
                            .build());
        } else {
            throw new ExpiredTokenException("Token has expired!");
        }
    }

    private void addCookieToResponse(HttpServletResponse response, String refreshToken) {
        var cookie = new Cookie("RefreshToken", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        // @TODO: change to production domain
        cookie.setDomain("localhost");
        cookie.setSecure(true);
        response.addCookie(cookie);
    }
}
