package org.example.authapi.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.authapi.DTOs.TransformRequest;
import org.example.authapi.clients.DataApiClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestService {
    private final DataApiClient dataApiClient;

    @Value("${internal.token}")
    private String internalToken;

    /*@PostConstruct
    public void test(){
        log.info(dataApiClient.transform(internalToken, new TransformRequest("Test")).getTextForResponse());
    }*/
}
