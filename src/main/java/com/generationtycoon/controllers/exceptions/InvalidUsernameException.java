package com.generationtycoon.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Eccezione lanciata per segnalare uno username non valido.
 */
@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class InvalidUsernameException extends RuntimeException {
    /**
     * Costruttore della classe.
     *
     * @param message il messaggio dell'eccezione.
     */
    public InvalidUsernameException(String message) {
        super(message);
    }
}
