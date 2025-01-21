package com.generationtycoon.model.dto;

import com.generationtycoon.model.entities.Difficulty;

import java.time.LocalDate;

public record UserScoreReqDto(LocalDate startTime, LocalDate endTime, int hp, Difficulty difficulty)
{

}
