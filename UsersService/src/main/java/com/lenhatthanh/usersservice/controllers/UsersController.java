package com.lenhatthanh.usersservice.controllers;

import com.lenhatthanh.usersservice.model.UserDto;
import com.lenhatthanh.usersservice.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final Environment environment;
    UsersService usersService;

    @Autowired
    public UsersController(Environment environment, UsersService usersService) {
        this.environment = environment;
        this.usersService = usersService;
    }

    @GetMapping("/status/check")
    public String status() {
        return "Working on port " + environment.getProperty("local.server.port");
    }

    @PostMapping
    public void create(@Valid @RequestBody UserDto userDto) {
        usersService.create(userDto);
    }
}
