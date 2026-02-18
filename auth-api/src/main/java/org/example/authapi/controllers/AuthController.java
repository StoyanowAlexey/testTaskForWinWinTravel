package org.example.authapi.controllers;

import lombok.RequiredArgsConstructor;
import org.example.authapi.entities.AuthResponse;
import org.example.authapi.entities.User;
import org.example.authapi.services.Auth.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registration(@RequestBody User user) {
        authService.registration(user);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse login(@RequestBody User user){
        String token = authService.auth(user);
        return new AuthResponse(token);
    }
}
