package org.example.authapi.services.Auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public String convertPasswordToBCryptCode(String password){
        return bCryptPasswordEncoder.encode(password);
    }

    public boolean checkPassword(String enteredPassword, String dataBasePassword) {
        return bCryptPasswordEncoder.matches(enteredPassword, dataBasePassword);
    }

}
