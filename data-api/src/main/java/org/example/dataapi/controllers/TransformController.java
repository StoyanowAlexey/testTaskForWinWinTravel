package org.example.dataapi.controllers;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.example.dataapi.DTOs.TextRequest;
import org.example.dataapi.DTOs.TextResponse;
import org.example.dataapi.services.TransformService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transform")
@RequiredArgsConstructor
@Slf4j
public class TransformController {

    private final TransformService transformService;

    @PostMapping
    public TextResponse transform(@RequestBody TextRequest request, @RequestHeader("X-Internal-Token") String xInternalToken) {
        log.info(transformService.transformText(request, xInternalToken));
        return new TextResponse(transformService.transformText(request, xInternalToken));
    }
}
