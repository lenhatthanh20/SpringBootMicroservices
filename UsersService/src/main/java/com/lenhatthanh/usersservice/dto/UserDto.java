package com.lenhatthanh.usersservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
public class UserDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -3026766936644492660L;
    private String firstName;
    private String lassName;
    private String password;
    private String email;
    private String id;
    private String encryptedPassword;
}
