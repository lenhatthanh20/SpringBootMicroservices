package com.lenhatthanh.usersservice.service;

import com.lenhatthanh.usersservice.exception.UserAlreadyExistException;
import com.lenhatthanh.usersservice.model.UserDto;
import com.lenhatthanh.usersservice.entity.UserEntity;
import com.lenhatthanh.usersservice.repository.UserRepositoryInterface;
import com.lenhatthanh.usersservice.shared.Messages;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class UsersService implements UsersServiceInterface {
    UserRepositoryInterface userRepositoryInterface;
    PasswordEncoder passwordEncoder;
    Messages messages;

    @Override
    public void create(UserDto userDto) {
        this.checkAndThrowIfUserExists(userDto.getEmail());
        UserEntity userEntity = createEntityFromDto(userDto);
        userRepositoryInterface.save(userEntity);
    }

    private void checkAndThrowIfUserExists(String email) {
        Optional<UserEntity> user = userRepositoryInterface.findByEmail(email);
        if (user.isPresent()) {
            throw new UserAlreadyExistException(messages.getMessage("error.application.emailAlreadyExist"));
        }
    }

    private UserEntity createEntityFromDto(UserDto userDto) {
        return UserEntity
                .builder()
                .userId(UUID.randomUUID().toString())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .build();
    }
}
