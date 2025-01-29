package com.generationtycoon.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Eccezione che rappresenta la mancanza di un oggetto Brainj.
 *<p>
 * Questa eccezione viene sollevata quando si tenta di accedere a un Brainj
 * che non è presente nel database. È annotata con {@link ResponseStatus} per restituire
 * un codice di stato HTTP 404 (NOT FOUND) al client, segnalando che la risorsa richiesta
 * non è stata trovata.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class BrainjMissingException extends RuntimeException {
    /**
     * Costruttore per creare un'istanza di {@link BrainjMissingException} con un messaggio specifico.
     *
     * @param message il messaggio che descrive l'errore.
     */
    public BrainjMissingException(String message) {
        super(message);
    }
}
