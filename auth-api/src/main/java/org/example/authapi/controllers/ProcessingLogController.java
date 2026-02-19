package org.example.authapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.authapi.DTOs.ErrorResponse;
import org.example.authapi.DTOs.TransformRequest;
import org.example.authapi.DTOs.TransformResponse;
import org.example.authapi.services.ProcessingLog.ProcessingLogService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@Tag(name = "Processing", description = "Endpoints for text processing")
@RequestMapping("/process")
public class ProcessingLogController {

    private final ProcessingLogService processingLogService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Process input text and return transformed result")
    @io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "bearerAuth")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Text to process",
            required = true,
            content = @Content(
                    schema = @Schema(implementation = TransformRequest.class),
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                            value = "{\"text\": \"Hello World\"}"
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Text processed successfully",
                    content = @Content(schema = @Schema(implementation = TransformResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request body",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden: invalid internal token",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public TransformResponse process(@RequestBody TransformRequest request){
        System.out.println("Request : " +  request.getText());
        return processingLogService.process(request);
    }

}

