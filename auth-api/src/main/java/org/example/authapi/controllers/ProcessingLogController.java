package org.example.authapi.controllers;

import lombok.RequiredArgsConstructor;
import org.example.authapi.DTOs.TransformRequest;
import org.example.authapi.DTOs.TransformResponse;
import org.example.authapi.services.ProcessingLog.ProcessingLogService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
public class ProcessingLogController {

    private final ProcessingLogService processingLogService;

    @PostMapping("/process")
    @ResponseStatus(HttpStatus.OK)
    public TransformResponse process(@RequestBody TransformRequest request){
        System.out.println("Request : " +  request.getTextFromRequest());
        return processingLogService.process(request);
    }

}

