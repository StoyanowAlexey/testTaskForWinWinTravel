package org.example.authapi.services.ProcessingLog;

import lombok.RequiredArgsConstructor;
import org.example.authapi.DTOs.TransformRequest;
import org.example.authapi.DTOs.TransformResponse;
import org.example.authapi.clients.DataApiClient;
import org.example.authapi.entities.ProcessingLog;
import org.example.authapi.exceptions.InvalidRequestException;
import org.example.authapi.repositories.ProcessingLogRepository;
import org.example.authapi.repositories.UserRepository;
import org.example.authapi.services.Auth.AuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProcessingLogService {
    private final AuthService authService;
    private final DataApiClient dataApiClient;
    private final ProcessingLogRepository processingLogRepository;
    private final UserRepository userRepository;

    @Value("${internal.token}")
    private String internalToken;

    public TransformResponse process(TransformRequest transformRequest) {
        if (transformRequest == null
                || transformRequest.getText() == null
                || transformRequest.getText().isBlank()) {
            throw new InvalidRequestException("Text must not be null or empty");
        }
        String email = authService.getEmailByAuthorization();
        ProcessingLog processingLog = new ProcessingLog();
        processingLog.setCreatedAt(LocalDateTime.now());
        processingLog.setInputText(transformRequest.getText());
        processingLog.setUserId(userRepository.getUserByEmail(email).getId());
        TransformResponse response = dataApiClient.transform(internalToken, transformRequest);
        if (response.getText() == null || response.getText().isBlank()) {
            throw new InvalidRequestException("Text is empty");
        }
        processingLog.setOutputText(response.getText());
        processingLogRepository.save(processingLog);
        return response;
    }
}
