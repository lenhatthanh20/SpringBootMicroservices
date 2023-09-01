package com.lenhatthanh.usersservice.exception;

public class UserAlreadyExistException extends ApplicationException {
    public UserAlreadyExistException(String message) {
        super(message);
    }
}
