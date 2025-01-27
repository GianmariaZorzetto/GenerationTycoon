package com.generationtycoon.controllers.exceptions.resolvers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
/**
 * Classe che gestisce le eccezioni globali nei controller REST.
 * <p>
 * Questa classe fornisce una gestione centralizzata delle eccezioni tramite
 * l'annotazione {@link ControllerAdvice}, consentendo di definire
 * come rispondere a determinati tipi di errori in modo uniforme.
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    /**
     * Metodo che gestisce le eccezioni di tipo {@link IllegalArgumentException}
     * e {@link NullPointerException}.
     * <p>
     * Questo metodo restituisce una risposta HTTP con codice di stato
     * {@link HttpStatus#INTERNAL_SERVER_ERROR} e il messaggio
     * dell'eccezione come corpo della risposta.
     *
     * @param ex l'eccezione sollevata.
     * @param request l'oggetto {@link WebRequest} associato alla richiesta corrente.
     * @return una {@link ResponseEntity} contenente il messaggio di errore e gli header HTTP.
     */
    @ExceptionHandler(value
            = {IllegalArgumentException.class, NullPointerException.class})
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}