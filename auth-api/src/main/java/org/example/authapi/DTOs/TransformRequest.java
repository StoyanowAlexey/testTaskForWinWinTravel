package org.example.authapi.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransformRequest {
    @Schema(description = "Text to be transformed", example = "Hello World", required = true)
    private String text;
}
