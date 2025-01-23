package com.generationtycoon.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Eccezione che viene lanciata quando una email di un utente non è presente nel database
 * o non è valida come esplicitato in {@link com.generationtycoon.utils.validator.Validator#EMAIL}.
 */
@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
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
