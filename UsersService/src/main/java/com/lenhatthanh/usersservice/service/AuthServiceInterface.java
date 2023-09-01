package com.lenhatthanh.usersservice.service;

import com.lenhatthanh.usersservice.model.LoginDto;
import com.lenhatthanh.usersservice.model.LoginResponseDto;

public interface AuthServiceInterface {
    LoginResponseDto login(LoginDto request);
}
