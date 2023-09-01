package com.lenhatthanh.usersservice.exception;

public class EmailAlreadyExistException extends ApplicationException {
    public EmailAlreadyExistException(String message) {
        super(message);
    }
}
