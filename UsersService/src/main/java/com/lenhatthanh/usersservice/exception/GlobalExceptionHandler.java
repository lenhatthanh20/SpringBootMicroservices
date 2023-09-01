package com.lenhatthanh.usersservice.exception;

import com.lenhatthanh.usersservice.shared.Messages;
import com.lenhatthanh.usersservice.shared.StringHelpers;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@AllArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {
    final private Messages messages;

    @ExceptionHandler({EmailAlreadyExistException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResponse handleAlreadyExistExceptions(ApplicationException exception, final HttpServletRequest request) {
        return createExceptionResponse(exception.getCode(), exception.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler({UserNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleNotFoundExceptions(ApplicationException exception, final HttpServletRequest request) {
        return createExceptionResponse(exception.getCode(), exception.getMessage(), request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionResponse handleRequestValidationExceptions(MethodArgumentNotValidException exception, final HttpServletRequest request) {
        FieldError firstError = exception.getFieldErrors().get(0);
        String[] splitError = StringHelpers.splitStringWithColon(Objects.requireNonNull(firstError.getDefaultMessage()));

        return createExceptionResponse(splitError[0], splitError[1], request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public ExceptionResponse handleBadCredentialsExceptions(AuthenticationException exception, final HttpServletRequest request) {
        String code = messages.getMessage("error.code.common.unauthorized");

        return createExceptionResponse(code, exception.getMessage(), request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public ExceptionResponse handleRuntimeExceptions(RuntimeException exception, final HttpServletRequest request) {
        String code = messages.getMessage("error.code.common.badRequest");

        return createExceptionResponse(code, exception.getMessage(), request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ExceptionResponse handleUnwantedExceptions(Exception exception, final HttpServletRequest request) {
        String code = messages.getMessage("error.code.common.unknown");

        return createExceptionResponse(code, exception.getMessage(), request.getRequestURI());
    }

    private ExceptionResponse createExceptionResponse(String code, String message, String uri) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setErrorCode(code);
        exceptionResponse.setErrorMessage(message);
        exceptionResponse.setRequestedURI(uri);

        return exceptionResponse;
    }
}
