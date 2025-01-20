package com.generationtycoon.model.dto;

import com.generationtycoon.model.entities.Difficulty;

public record UserRegistrationReqDto(String email, String username, String password, Difficulty difficulty)
{
}
