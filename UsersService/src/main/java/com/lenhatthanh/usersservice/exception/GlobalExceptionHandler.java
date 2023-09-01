package com.lenhatthanh.usersservice.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ UserAlreadyExistException.class })
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResponse handleAlreadyExist(ApplicationException exception, final HttpServletRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setErrorCode(exception.getCode());
        exceptionResponse.setErrorMessage(exception.getMessage());
        exceptionResponse.setRequestedURI(request.getRequestURI());

        return exceptionResponse;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionResponse handleValidationExceptions(MethodArgumentNotValidException exception, final HttpServletRequest request) {
        FieldError firstError = exception.getFieldErrors().get(0);

        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setErrorMessage(firstError.getDefaultMessage());
        exceptionResponse.setRequestedURI(request.getRequestURI());
        exceptionResponse.setErrorCode("ERROR-00002");

        return exceptionResponse;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleUnwantedException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unknown error");
    }
}
