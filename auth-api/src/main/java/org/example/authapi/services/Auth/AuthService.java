package org.example.authapi.services.Auth;

import lombok.RequiredArgsConstructor;
import org.example.authapi.DTOs.CreateUserRequest;
import org.example.authapi.DTOs.LoginUserRequest;
import org.example.authapi.entities.User;
import org.example.authapi.exceptions.EntityAlreadyExistException;
import org.example.authapi.exceptions.InvalidCredentialsException;
import org.example.authapi.exceptions.InvalidJwtException;
import org.example.authapi.mappers.UserMapper;
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
    private final UserMapper userMapper;

    public void registration(CreateUserRequest createUserRequest) {
        if (userRepository.existsByEmail(createUserRequest.getEmail())) {
            throw new EntityAlreadyExistException("User with this email already exists");
        }

        User user = userMapper.toUser(createUserRequest);
        User savingUser = new User(
                user.getEmail(),
                passwordService.convertPasswordToBCryptCode(user.getPassword())
        );

        userRepository.save(savingUser);
    }

    public String auth(LoginUserRequest loginUserRequest){
        if (!userRepository.existsByEmail(loginUserRequest.getEmail())) {
            throw new InvalidCredentialsException("User with this email does not exist!");
        }
        if(!passwordService.checkPassword(loginUserRequest.getPassword(), userRepository.getUserByEmail(loginUserRequest.getEmail()).getPassword())) {
            throw new InvalidCredentialsException("Fake password!");
        }
        return jwtService.generateToken(loginUserRequest.getEmail());
    }

    public String getEmailByAuthorization(){
        Authentication token = SecurityContextHolder.getContext().getAuthentication();
        if (token == null || !token.isAuthenticated()) {
            throw new InvalidJwtException("User not authenticated!");
        }
        return token.getName();
    }
}
