package com.generationtycoon.model.entities;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KaboomColorsTest {

    @ParameterizedTest
    @ValueSource(strings = {"red", "RED", "Red", "rosso", "ROSSO", "Rosso"})
    void fromStringRed(String input) {
        assertEquals(KaboomColors.RED, KaboomColors.fromString(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"blue", "BLUE", "Blue", "blu", "BLU", "Blu"})
    void fromStringBlue(String input) {
        assertEquals(KaboomColors.BLUE, KaboomColors.fromString(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"yellow", "YELLOW", "Yellow", "Giallo", "GIALLO", "giallo"})
    void fromStringYellow(String input) {
        assertEquals(KaboomColors.YELLOW, KaboomColors.fromString(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"green", "Green", "GREEN", "Verde", "VERDE", "verde"})
    void fromStringGreen(String input) {
        assertEquals(KaboomColors.GREEN, KaboomColors.fromString(input));
    }
}