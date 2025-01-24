package com.generationtycoon.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.generationtycoon.model.entities.Difficulty;

import java.time.LocalDateTime;

/**
 * Dto per la richiesta di update dello score.
 *
 * @param startTime  l'orario di inizio del quiz.
 * @param endTime    l'orario di fine del quiz.
 * @param hp         gli hp rimanenti.
 * @param difficulty la difficoltà.
 */
@JsonSerialize
@JsonDeserialize(as = UserScoreReqDto.class)
public record UserScoreReqDto(
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        LocalDateTime startTime,
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        LocalDateTime endTime,
        int hp,
        Difficulty difficulty
) {
}
