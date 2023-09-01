package com.lenhatthanh.usersservice.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    @NotNull(message="{error.validation.user.email.required}")
    @Email(message="{error.validation.user.email.format}")
    private String email;

    @NotNull(message="{error.validation.user.password.required}")
    @Size(min=8, max=16, message="{error.validation.user.password.size}")
    private String password;
}
