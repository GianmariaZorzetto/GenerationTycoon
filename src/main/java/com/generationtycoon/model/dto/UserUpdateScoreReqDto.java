package com.generationtycoon.model.dto;

/**
 * Dto per aggiornare il punteggio di un nuovo utente.
 *
 * @param id    l'id dell'utente.
 * @param score lo score da aggiornare.
 */
public record UserUpdateScoreReqDto(Long id, Double score) {
}
