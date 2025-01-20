package com.generationtycoon.model.dto;

import com.generationtycoon.model.entities.Difficulty;

public record UserLeaderboardRespDto(String username, Difficulty difficulty, double score)
{
}
