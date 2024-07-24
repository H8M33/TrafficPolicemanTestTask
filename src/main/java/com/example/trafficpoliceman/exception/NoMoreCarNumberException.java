package com.example.trafficpoliceman.exception;

import org.springframework.http.HttpStatus;

public class NoMoreCarNumberException extends ServiceException{
    public NoMoreCarNumberException() {
        super("All car numbers are in use", HttpStatus.CONFLICT);
    }
}
