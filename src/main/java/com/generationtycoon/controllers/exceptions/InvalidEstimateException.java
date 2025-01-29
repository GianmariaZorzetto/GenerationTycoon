package com.generationtycoon.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Eccezione che rappresenta un'operazione non valida a causa di una stima errata.
 * <p>
 * Questa eccezione viene lanciata quando il servizio di calcolo dello score restituisce un valore nullo.
 * È annotata con {@link ResponseStatus} per restituire un codice di stato HTTP 400 (BAD REQUEST)
 * al client, segnalando che la richiesta contiene dati non validi.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidEstimateException extends RuntimeException {
    /**
     * Costruttore per creare un'istanza di {@link InvalidEstimateException} con un messaggio specifico.
     *
     * @param message il messaggio che descrive l'errore.
     */
    public InvalidEstimateException(String message) {
        super(message);
    }
}
