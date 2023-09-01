package com.lenhatthanh.usersservice.exception;

public class InvalidEmailOrPasswordException extends ApplicationException {
    public InvalidEmailOrPasswordException(String message) {
        super(message);
    }
}
