package com.generationtycoon.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class KaboomMissingException extends RuntimeException {
    public KaboomMissingException(String message) {
        super(message);
    }
}