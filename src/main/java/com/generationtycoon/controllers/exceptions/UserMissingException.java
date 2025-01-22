package com.generationtycoon.controllers.exceptions;

/**
 * Eccezione che viene lanciata per segnalare un utente non presente.
 */
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
