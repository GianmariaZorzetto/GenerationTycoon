package com.generationtycoon.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Eccezione lanciata quando una password non è conforma e {@link com.generationtycoon.utils.validator.Validator#PASSWORD}.
 */
@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class InvalidPasswordException extends RuntimeException {
    /**
     * Costruttore della classe.
     *
     * @param message il messaggio dell'eccezione.
     */
    public InvalidPasswordException(String message) {
        super(message);
    }
}
