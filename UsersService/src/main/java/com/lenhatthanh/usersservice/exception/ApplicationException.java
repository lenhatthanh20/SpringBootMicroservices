package com.lenhatthanh.usersservice.exception;

import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {
    private String code;
    private String message;

    public ApplicationException(String message) {
        super(message);
        this.splitMessageAndCode(message);
    }

    /**
     * Error message will be defined with format: {error-code}: {error-message}
     * We will split the error code and message separately
     */
    private void splitMessageAndCode(String message) {
        String[] parts = message.split(":", 2);
        this.message = parts[1].trim();
        this.code = parts[0].trim();
    }
}
