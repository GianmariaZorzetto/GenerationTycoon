package com.generationtycoon.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.generationtycoon.model.entities.Difficulty;


/**
 * @param token
 * @param id
 * @param difficulty la difficoltà da resettare speriamo che dio me la mandi buona
 */
@JsonSerialize
@JsonDeserialize(as = UserResetReqDto.class)
public record UserResetReqDto(
        String token,
        Long id,
        Difficulty difficulty
) {
}
