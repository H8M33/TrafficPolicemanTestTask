package com.example.trafficpoliceman.exception;

import org.springframework.http.HttpStatus;

public class PreviousNumberNotFoundException extends ServiceException{
    public PreviousNumberNotFoundException() {
        super("Previous car number not found", HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
