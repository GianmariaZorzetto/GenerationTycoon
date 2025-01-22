package com.generationtycoon.controllers.exceptions;

/**
 * Eccezione che viene lanciata quando un token non è valido.
 */
public class InvalidTokenException extends RuntimeException {
    /**
     * Costruttore della classe.
     *
     * @param message il messaggio dell'eccezione.
     */
    public InvalidTokenException(String message) {
        super(message);
    }
}
