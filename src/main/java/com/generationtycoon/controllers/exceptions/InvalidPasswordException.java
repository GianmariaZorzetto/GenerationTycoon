package com.generationtycoon.controllers.exceptions;

/**
 * Eccezione lanciata quando una password non è conforma e {@link com.generationtycoon.utils.validator.Validator#PASSWORD}.
 */
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
