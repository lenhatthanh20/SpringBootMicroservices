package com.lenhatthanh.usersservice.service;

import com.lenhatthanh.usersservice.exception.UserAlreadyExistException;
import com.lenhatthanh.usersservice.model.UserDto;
import com.lenhatthanh.usersservice.entity.UserEntity;
import com.lenhatthanh.usersservice.repository.UserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UsersService implements UsersServiceInterface {
    UserRepositoryInterface userRepositoryInterface;
    PasswordEncoder passwordEncoder;

    @Autowired
    public UsersService(UserRepositoryInterface userRepositoryInterface, PasswordEncoder passwordEncoder) {
        this.userRepositoryInterface = userRepositoryInterface;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void create(UserDto userDto) {
        Optional<UserEntity> user = userRepositoryInterface.findByEmail(userDto.getEmail());
        if(user.isPresent()) {
            throw new UserAlreadyExistException(userDto.getEmail());
        }

        UserEntity userEntity = UserEntity.builder()
                .userId(UUID.randomUUID().toString())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .build();

        userRepositoryInterface.save(userEntity);
    }
}
