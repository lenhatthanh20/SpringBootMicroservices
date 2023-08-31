package com.lenhatthanh.usersservice.exception;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String email) {
        super("The email " + email + " already exist");
    }
}
