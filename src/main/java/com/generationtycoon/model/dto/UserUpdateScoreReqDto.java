package com.generationtycoon.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Dto per aggiornare il punteggio di un nuovo utente.
 *
 * @param id    l'id dell'utente.
 * @param score lo score da aggiornare.
 */
@JsonSerialize
@JsonDeserialize(as = UserUpdateScoreReqDto.class)
public record UserUpdateScoreReqDto(Long id, Double score) {
}
