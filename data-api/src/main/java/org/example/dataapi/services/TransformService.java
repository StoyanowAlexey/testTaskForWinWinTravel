package org.example.dataapi.services;

import lombok.RequiredArgsConstructor;
import org.example.dataapi.DTOs.TextRequest;
import org.example.dataapi.exceptions.InvalidRequestException;
import org.example.dataapi.exceptions.InvalidXInternalToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransformService {

    private final XInternalTokenService xInternalTokenService;

    private final TextHashService textHashService;

    public String transformText(TextRequest request, String xInternalToken){
        if (xInternalTokenService.isEqualTokens(xInternalToken)) {
            throw new InvalidXInternalToken("Invalid XInternalToken");
        }
        if (request == null || request.getTextRequest().isBlank()) {
            throw new InvalidRequestException("Text must not be null or empty");
        }
        return textHashService.transformText(request.getTextRequest());
    }
}
