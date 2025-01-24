package com.generationtycoon.model.entities;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifficultyTest {

    @ParameterizedTest
    @ValueSource(strings = {"facile", "Facile", "FACILE", "easy", "EASY", "Easy"})
    void getDifficultyEasy(String difficulty) {
        assertEquals(Difficulty.EASY, Difficulty.fromString(difficulty));
    }

    @ParameterizedTest
    @ValueSource(strings = {"intermedio", "Intermedio", "INTERMEDIO", "medium", "MEDIUM", "Medium"})
    void getDifficultyMedium(String difficulty) {
        assertEquals(Difficulty.MEDIUM, Difficulty.fromString(difficulty));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Avanzato", "AVANZATO", "avanzato", "hard", "HARD", "Hard"})
    void getDifficultyHard(String difficulty) {
        assertEquals(Difficulty.HARD, Difficulty.fromString(difficulty));
    }
}