package com.generationtycoon.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)


public class InvalidEstimateException extends RuntimeException {
    public InvalidEstimateException(String message) {
        super(message);
    }
}
