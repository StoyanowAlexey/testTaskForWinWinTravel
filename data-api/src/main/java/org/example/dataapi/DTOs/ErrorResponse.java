package org.example.dataapi.DTOs;

public record ErrorResponse(
        String message,
        int status,
        String timestamp
) {}
