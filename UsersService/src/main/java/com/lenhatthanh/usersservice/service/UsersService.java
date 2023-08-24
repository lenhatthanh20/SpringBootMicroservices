package com.lenhatthanh.usersservice.service;

import com.lenhatthanh.usersservice.dto.UserDto;
import com.lenhatthanh.usersservice.entity.UserEntity;
import com.lenhatthanh.usersservice.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsersService implements UsersServiceInterface{
    UserRepository userRepository;

    @Autowired
    public UsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto create(UserDto userDto) {
        userDto.setId(UUID.randomUUID().toString());

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userEntity, userDto);

        userEntity.setPassword("test");

        userRepository.save(userEntity);
        return null;
    }
}
