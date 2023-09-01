package com.lenhatthanh.usersservice.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {
    private String userId;

    @NotNull(message="{error.validation.user.firstName.required}")
    @Size(min=2, message="{error.validation.user.firstName.size}")
    private String firstName;

    @NotNull(message="{error.validation.user.lastName.required}")
    @Size(min=2, message="{error.validation.user.lastName.size}")
    private String lastName;

    @NotNull(message="{error.validation.user.password.required}")
    @Size(min=8, max=16, message="{error.validation.user.password.size}")
    private String password;

    @NotNull(message="{error.validation.user.email.required}")
    @Email(message="{error.validation.user.email.format}")
    private String email;
}
