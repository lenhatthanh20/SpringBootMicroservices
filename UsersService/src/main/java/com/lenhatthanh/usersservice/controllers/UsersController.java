package com.lenhatthanh.usersservice.controllers;

import com.lenhatthanh.usersservice.dto.UserDto;
import com.lenhatthanh.usersservice.model.UserRequest;
import com.lenhatthanh.usersservice.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
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
    public String create(@Valid @RequestBody UserRequest userRequest) {

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDto, userRequest);
        usersService.create(userDto);

        return "";
    }
}
