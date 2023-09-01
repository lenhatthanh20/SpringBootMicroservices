package com.lenhatthanh.usersservice.controllers;

import com.lenhatthanh.usersservice.model.LoginDto;
import com.lenhatthanh.usersservice.model.UserDto;
import com.lenhatthanh.usersservice.service.AuthServiceInterface;
import com.lenhatthanh.usersservice.service.UsersService;
import com.lenhatthanh.usersservice.service.UsersServiceInterface;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UsersController {

    private final Environment environment;
    UsersServiceInterface usersService;
    AuthServiceInterface authService;

    @GetMapping("/status/check")
    public String status() {
        return "Working on port " + environment.getProperty("local.server.port");
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody UserDto userDto) {
        usersService.create(userDto);
    }

    @PostMapping("/auth/login")
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestBody LoginDto loginUser) {
        return authService.login(loginUser);
    }
}
