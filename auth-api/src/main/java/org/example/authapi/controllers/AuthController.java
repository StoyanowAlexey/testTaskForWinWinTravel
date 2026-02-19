package org.example.authapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.authapi.DTOs.CreateUserRequest;
import org.example.authapi.DTOs.ErrorResponse;
import org.example.authapi.DTOs.LoginUserRequest;
import org.example.authapi.entities.AuthResponse;
import org.example.authapi.entities.User;
import org.example.authapi.services.Auth.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Endpoints for user registration and login")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Register a new user")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "User registration payload",
            required = true,
            content = @Content(schema = @Schema(implementation = CreateUserRequest.class))
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "User already exists",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public void registration(@RequestBody CreateUserRequest createUserRequest) {
        authService.registration(createUserRequest);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Authenticate user and get JWT token")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "User login payload",
            required = true,
            content = @Content(schema = @Schema(implementation = LoginUserRequest.class))
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authentication successful",
                    content = @Content(schema = @Schema(implementation = AuthResponse.class))),
            @ApiResponse(responseCode = "401", description = "Invalid credentials",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request format",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public AuthResponse login(@RequestBody LoginUserRequest loginUserRequest){
        String token = authService.auth(loginUserRequest);
        return new AuthResponse(token);
    }
}
