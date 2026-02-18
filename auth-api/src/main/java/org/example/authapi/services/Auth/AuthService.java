package org.example.authapi.services.Auth;

import lombok.RequiredArgsConstructor;
import org.example.authapi.entities.User;
import org.example.authapi.exceptions.EntityAlreadyExistException;
import org.example.authapi.exceptions.InvalidCredentialsException;
import org.example.authapi.exceptions.InvalidJwtException;
import org.example.authapi.repositories.UserRepository;
import org.example.authapi.services.Jwt.JwtService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordService passwordService;
    private final JwtService jwtService;

    public void registration(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EntityAlreadyExistException("User with this email already exists");
        }

        User savingUser = new User(
                user.getEmail(),
                passwordService.convertPasswordToBCryptCode(user.getPassword())
        );

        userRepository.save(savingUser);
    }

    public String auth(User user){
        if (!userRepository.existsByEmail(user.getEmail())) {
            throw new InvalidCredentialsException("User with this email does not exist!");
        }
        if(!passwordService.checkPassword(user.getPassword(), userRepository.getUserByEmail(user.getEmail()).getPassword())) {
            throw new InvalidCredentialsException("Fake password!");
        }
        return jwtService.generateToken(user.getEmail());
    }

    public String getEmailByAuthorization(){
        Authentication token = SecurityContextHolder.getContext().getAuthentication();
        if (token == null || !token.isAuthenticated()) {
            throw new InvalidJwtException("User not authenticated!");
        }
        return token.getName();
    }
}
