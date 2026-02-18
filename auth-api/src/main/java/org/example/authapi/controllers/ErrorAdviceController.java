package org.example.authapi.controllers;

import org.example.authapi.exceptions.EntityAlreadyExistException;
import org.example.authapi.exceptions.InvalidCredentialsException;
import org.example.authapi.exceptions.InvalidJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ErrorAdviceController {

    @ExceptionHandler(EntityAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> handleEntityAlreadyExist(EntityAlreadyExistException ex) {
        return Map.of("message", ex.getMessage());
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> handleEntityDoesntExist(InvalidCredentialsException ex) {
        return Map.of("message", ex.getMessage());
    }

    @ExceptionHandler(InvalidJwtException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleInvalidJwtToken(InvalidJwtException ex) {
        return Map.of("message", ex.getMessage());
    }


}
