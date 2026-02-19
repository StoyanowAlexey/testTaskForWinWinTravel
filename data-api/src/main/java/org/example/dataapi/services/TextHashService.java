package org.example.dataapi.services;

import org.springframework.stereotype.Service;

@Service
public class TextHashService {

    public String transformText(String text) {
        return new StringBuilder(text)
                .reverse()
                .toString()
                .toUpperCase();
    }

}
