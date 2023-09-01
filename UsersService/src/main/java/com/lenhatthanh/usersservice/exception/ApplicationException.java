package com.lenhatthanh.usersservice.exception;

import com.lenhatthanh.usersservice.shared.StringHelpers;
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
        String[] splitMessage = StringHelpers.splitStringWithColon(message);
        this.code = splitMessage[0];
        this.message = splitMessage[1];
    }
}
