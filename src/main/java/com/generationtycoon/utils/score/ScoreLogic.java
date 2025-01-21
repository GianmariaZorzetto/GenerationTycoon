package com.generationtycoon.utils.score;

import com.generationtycoon.model.dto.UserScoreReqDto;
import com.generationtycoon.model.entities.Difficulty;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class ScoreLogic {
    public @Nullable Double calculateScore(UserScoreReqDto dto) {
        if (dto == null) return null;
        Difficulty difficulty = dto.difficulty();
        if (difficulty == null) return null;
        LocalDateTime start = dto.startTime();
        LocalDateTime end = dto.endTime();
        if (start == null || end == null || start.isAfter(end)) {
            return null;
        }
        double diff = 1000 - ChronoUnit.MINUTES.between(start, end);
        double score = diff * difficulty.multiplier;
        switch (difficulty) {
            case EASY -> score += 100 * dto.hp();
            case MEDIUM -> score += 300 * dto.hp();
            case HARD -> score += 500;
            default -> throw new IllegalArgumentException("Difficulty non riconosciuta.");
        }
        return score;
    }
}
