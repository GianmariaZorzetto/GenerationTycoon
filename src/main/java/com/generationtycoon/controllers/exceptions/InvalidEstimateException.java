package com.generationtycoon.controllers.exceptions;

public class InvalidEstimateException extends RuntimeException {
    public InvalidEstimateException(String message) {
        super(message);
    }
}
