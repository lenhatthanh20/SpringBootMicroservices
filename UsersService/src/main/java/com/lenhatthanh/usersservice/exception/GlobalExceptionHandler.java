package com.lenhatthanh.usersservice.exception;

import com.lenhatthanh.usersservice.shared.StringHelpers;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ EmailAlreadyExistException.class })
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

        String[] splitError = StringHelpers.splitStringWithColon(Objects.requireNonNull(firstError.getDefaultMessage()));
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setErrorCode(splitError[0]);
        exceptionResponse.setErrorMessage(splitError[1]);
        exceptionResponse.setRequestedURI(request.getRequestURI());

        return exceptionResponse;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleUnwantedException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unknown error");
    }
}
