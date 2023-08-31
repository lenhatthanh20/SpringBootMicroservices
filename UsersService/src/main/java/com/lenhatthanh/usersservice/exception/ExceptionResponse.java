package com.lenhatthanh.usersservice.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResponse {
    private String errorCode;
    private String errorMessage;
    private String requestedURI;
}
