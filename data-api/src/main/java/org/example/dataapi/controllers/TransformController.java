package org.example.dataapi.controllers;

import lombok.RequiredArgsConstructor;
import org.example.dataapi.DTOs.TextRequest;
import org.example.dataapi.DTOs.TextResponse;
import org.example.dataapi.services.TransformService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transform")
@RequiredArgsConstructor
public class TransformController {

    private final TransformService transformService;

    @PostMapping
    public TextResponse transform(@RequestBody TextRequest request, @RequestHeader("X-Internal-Token") String xInternalToken) {
        return new TextResponse(transformService.transformText(request, xInternalToken));
    }
}
