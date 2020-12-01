package com.TimeForStudy.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> customHandleNotFound(HttpServerErrorException.InternalServerError ex) {
        return new ResponseEntity<>(getErrorMessage(ex), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> customHandleNotFound(RuntimeException ex) {
        return new ResponseEntity<>(getErrorMessage(ex), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> customBadCredits(BadCredentialsException ex) {
        return new ResponseEntity<>(getErrorMessage(ex), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> customAuthEx(AuthenticationException ex) {
        return new ResponseEntity<>(getErrorMessage(ex), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> customAuthEx(AccessDeniedException ex) {
        return new ResponseEntity<>(getErrorMessage(ex), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<Object> customAuthEx(HttpServerErrorException ex) {
        return new ResponseEntity<>(getErrorMessage(ex), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String getErrorMessage(Exception ex){
        String message = "time: " + new Date() +
                "\nerror: " + ex.getMessage() +
                "\n\ncause: ";
        for (StackTraceElement el: ex.getStackTrace()) {
            message = message.concat(el.getClassName() + "." + el.getMethodName() + "\n");
        }
        return message;
    }

}