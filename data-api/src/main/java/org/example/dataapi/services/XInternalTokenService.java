package org.example.dataapi.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class XInternalTokenService {
    @Value("${internal.token}")
    private String internalToken;

    public boolean isEqualTokens(String token){
        return Objects.equals(token, internalToken);
    }
}
