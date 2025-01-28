package com.generationtycoon.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * DTO per la risposta relativa al punteggio di un utente.
 *
 * @param score il punteggio dell'utente.
 */
@JsonSerialize
@JsonDeserialize(as = UserScoreRespDto.class)
public record UserScoreRespDto(Double score) {
}
