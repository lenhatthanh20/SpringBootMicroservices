package com.lenhatthanh.usersservice.service;

import com.lenhatthanh.usersservice.model.LoginDto;

public interface AuthServiceInterface {
    String login(LoginDto request);
}
