package org.example.authapi.DTOs;

public record ErrorResponse(
        String message,
        int status,
        String timestamp
) {}
