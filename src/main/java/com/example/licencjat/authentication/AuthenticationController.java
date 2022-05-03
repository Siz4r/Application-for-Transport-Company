package com.example.licencjat.authentication;

import com.example.licencjat.UI.Annotations.PreAuthorizeAny;
import com.example.licencjat.authentication.models.AuthenticateRequest;
import com.example.licencjat.authentication.models.AuthenticationDto;
import com.example.licencjat.userData.UserService;
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
            System.out.println(e.getMessage());
            System.out.println(authenticateRequest.getUsername());
            throw new BadCredentialsException("Wrong login or password!");
        }

        final var userDetails = userService
                .loadUserByUsername(authenticateRequest.getUsername());

        addCookieToResponse(response, jwtTokenUtil.generateToken(userDetails, 1000 * 60 * 60 * 24 * 7), 1000 * 60 * 60 * 24 * 7);

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

    @PostMapping("/logout")
    @PreAuthorizeAny
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> logout(HttpServletResponse response) {
        addCookieToResponse(response, null, 0);
        return ResponseEntity.ok(true);
    }

    private void addCookieToResponse(HttpServletResponse response, String refreshToken, int age) {
        var cookie = new Cookie("RefreshToken", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        // @TODO: change to production domain
        cookie.setDomain("localhost");
        cookie.setSecure(true);
        cookie.setMaxAge(age);
        response.addCookie(cookie);
    }
}
