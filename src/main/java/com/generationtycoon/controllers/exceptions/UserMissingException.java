package com.generationtycoon.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Eccezione che viene lanciata per segnalare un utente non presente.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserMissingException extends RuntimeException {
    /**
     * Costruttore della classe.
     *
     * @param message il messaggio dell'eccezione.
     */
    public UserMissingException(String message) {
        super(message);
    }
}
