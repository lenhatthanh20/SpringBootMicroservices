package com.lenhatthanh.usersservice.service;

import com.lenhatthanh.usersservice.exception.InvalidEmailOrPasswordException;
import com.lenhatthanh.usersservice.model.LoginDto;
import com.lenhatthanh.usersservice.model.UserEntity;
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
    Messages messages;

    @Override
    public String login(LoginDto request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserEntity userEntity = usersRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidEmailOrPasswordException(messages.getMessage("error.application.invalidEmailOrPassword")));

        return jwtService.generateToken(new User(userEntity.getEmail(), userEntity.getPassword(), true, true, true, true, new ArrayList<>()));
    }
}
