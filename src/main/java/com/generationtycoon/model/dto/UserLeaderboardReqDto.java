package com.generationtycoon.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Dto per la richiesta nella leaderboard.
 *
 * @param username lo username.
 */
@JsonSerialize
@JsonDeserialize(as = UserLeaderboardReqDto.class)
public record UserLeaderboardReqDto(String username) {
}
