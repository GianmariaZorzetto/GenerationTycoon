package com.generationtycoon.controllers.exceptions;

public class UserMissingException extends RuntimeException {
    public UserMissingException(String message) {
        super(message);
    }
}
