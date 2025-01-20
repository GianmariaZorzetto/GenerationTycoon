package com.generationtycoon.model.entities;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifficultyTest {

    @ParameterizedTest
    @ValueSource(strings = {"facile", "Facile", "FACILE"})
    void getDifficultyEasy(String difficulty) {
        assertEquals(Difficulty.EASY, Difficulty.fromString(difficulty));
    }

    @ParameterizedTest
    @ValueSource(strings = {"intermedio", "Intermedio", "INTERMEDIO"})
    void getDifficultyMedium(String difficulty) {
        assertEquals(Difficulty.MEDIUM, Difficulty.fromString(difficulty));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Avanzato", "AVANZATO", "avanzato"})
    void getDifficultyHard(String difficulty) {
        assertEquals(Difficulty.HARD, Difficulty.fromString(difficulty));
    }
}