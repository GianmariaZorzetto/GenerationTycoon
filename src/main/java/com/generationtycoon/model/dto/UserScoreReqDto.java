package com.generationtycoon.model.dto;

import com.generationtycoon.model.entities.Difficulty;

import java.time.LocalDateTime;

public record UserScoreReqDto(LocalDateTime startTime, LocalDateTime endTime, int hp,
                              Difficulty difficulty) {

}
