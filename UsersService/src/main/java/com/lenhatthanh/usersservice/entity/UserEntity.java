package com.lenhatthanh.usersservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name="users")
public class UserEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 6009937215357249661L;

    @Id
    @GeneratedValue
    private String id;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, length = 120, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String password;
}
