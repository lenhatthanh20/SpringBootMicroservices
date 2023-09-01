package com.lenhatthanh.usersservice.service;

import com.lenhatthanh.usersservice.model.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsersServiceInterface {
    void create(UserDto user);

    UserDetailsService userDetailsService();
}
