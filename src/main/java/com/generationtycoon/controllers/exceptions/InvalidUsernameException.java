package com.generationtycoon.controllers.exceptions;

/**
 * Eccezione lanciata per segnalare uno username non valido.
 */
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
