package com.lenhatthanh.usersservice.service;

import com.lenhatthanh.usersservice.exception.UserNotFoundException;
import com.lenhatthanh.usersservice.model.LoginDto;
import com.lenhatthanh.usersservice.model.UserEntity;
import com.lenhatthanh.usersservice.model.LoginResponseDto;
import com.lenhatthanh.usersservice.repository.UserRepositoryInterface;
import com.lenhatthanh.usersservice.shared.Messages;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@AllArgsConstructor
@Service
public class AuthService implements AuthServiceInterface {
    private final UserRepositoryInterface usersRepository;
    private final JwtServiceInterface jwtService;
    private final AuthenticationManager authenticationManager;
    private final Messages messages;

    @Override
    public LoginResponseDto login(LoginDto request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserEntity userEntity = usersRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException(messages.getMessage("error.application.userNotFound")));
        String token = jwtService.generateToken(new User(userEntity.getEmail(), userEntity.getPassword(), true, true, true, true, new ArrayList<>()));

        return new LoginResponseDto(userEntity.getUserId(), token);
    }
}
