package com.lenhatthanh.usersservice.service;

import com.lenhatthanh.usersservice.exception.EmailAlreadyExistException;
import com.lenhatthanh.usersservice.exception.UserNotFoundException;
import com.lenhatthanh.usersservice.model.UserDto;
import com.lenhatthanh.usersservice.model.UserEntity;
import com.lenhatthanh.usersservice.repository.UserRepositoryInterface;
import com.lenhatthanh.usersservice.shared.Messages;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class UsersService implements UsersServiceInterface {
    UserRepositoryInterface usersRepository;
    PasswordEncoder passwordEncoder;
    Messages messages;

    @Override
    public void create(UserDto userDto) {
        this.checkAndThrowErrorIfUserExists(userDto.getEmail());
        UserEntity userEntity = createEntityFromDto(userDto);
        usersRepository.save(userEntity);
    }

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String email) {
                UserEntity userEntity = usersRepository.findByEmail(email).orElseThrow(()
                        -> new UserNotFoundException(messages.getMessage("error.application.userNotFound"))
                );

                return new User(userEntity.getEmail(), userEntity.getPassword(), true, true, true, true, new ArrayList<>());
            }
        };
    }

    private void checkAndThrowErrorIfUserExists(String email) {
        Optional<UserEntity> user = usersRepository.findByEmail(email);
        if (user.isPresent()) {
            throw new EmailAlreadyExistException(messages.getMessage("error.application.emailAlreadyExist"));
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
