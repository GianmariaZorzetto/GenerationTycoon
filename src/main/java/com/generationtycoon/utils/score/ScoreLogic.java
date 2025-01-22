package com.generationtycoon.utils.score;

import com.generationtycoon.model.dto.UserScoreReqDto;
import com.generationtycoon.model.entities.Difficulty;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Classe di servizio per il calcolo della logica del punteggio di un quiz completato da un giocatore.
 */
@Service
public class ScoreLogic {
    /**
     * Metodo per il calcolo del punteggio.
     *
     * @param dto uno {@link UserScoreReqDto}, contenente tutte le info per il calcolo del punteggio.
     * @return il punteggio calcolato in {@link Double}; se {@code dto} o uno dei suoi parametri è {@code null} allora il metodo restituisce {@code null}.
     */
    public @Nullable Double calculateScore(UserScoreReqDto dto) {
        if (dto == null) return null;
        LocalDateTime start = dto.startTime();
        LocalDateTime end = dto.endTime();
        if (start == null || end == null || start.isAfter(end)) return null;
        Difficulty difficulty = dto.difficulty();
        if (difficulty == null) return null;
        double diff = 1000 - ChronoUnit.MINUTES.between(start, end);
        double score = diff * difficulty.multiplier;
        switch (difficulty) {
            case EASY -> score += 100 * dto.hp();
            case MEDIUM -> score += 300 * dto.hp();
            case HARD -> score += 1000;
            default -> {
                return null;
            }
        }
        return score;
    }
}
