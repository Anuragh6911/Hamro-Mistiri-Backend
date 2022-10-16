package com.example.hamromistiri.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AppException extends Exception {
    private HttpStatus status;

    public AppException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
