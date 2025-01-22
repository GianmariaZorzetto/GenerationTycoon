package com.generationtycoon.controllers.exceptions;

/**
 * Eccezione che viene lanciata quando una email di un utente non è presente nel database
 * o non è valida come esplicitato in {@link com.generationtycoon.utils.validator.Validator#EMAIL}.
 */
public class InvalidEmailException extends RuntimeException {
    /**
     * Costruttore della classe.
     *
     * @param message il messaggio dell'eccezione.
     */
    public InvalidEmailException(String message) {
        super(message);
    }
}
