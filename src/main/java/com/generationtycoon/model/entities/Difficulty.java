package com.generationtycoon.model.entities;

import org.springframework.lang.Nullable;

/**
 * {@link Enum} che rappresenta la difficoltà della partita.</br>
 * Ogni difficoltà ha un {@code multiplier}.</br>
 */
public enum Difficulty {
    /**
     * Difficoltà facile.
     */
    EASY(1),
    /**
     * Difficoltà intermedia.
     */
    MEDIUM(2),
    /**
     * Difficoltà avanzata.
     */
    HARD(3);

    /**
     * Il moltiplicatore della difficoltà.
     */
    public final int multiplier;

    /**
     * Costruttore della classe.
     *
     * @param multiplier il moltiplicatore della classe.
     */
    Difficulty(int multiplier) {
        this.multiplier = multiplier;
    }

    /**
     * Metodo che restituisce una difficoltà in base a una {@link String}.
     * Data una {@code difficulty} come {@link String} in ingresso il metodo restituisce un enum secondo il seguente schema:
     * <ul>
     *     <li>{@code difficoltà} -&gt; una {@code Difficulty}</li>
     *     <li>{@code qualsiasiAltraStringa} -&gt; {@code null}</li>
     * </ul>
     * Il metodo è case insensitive.
     *
     * @param difficulty la stringa in ingresso.
     * @return {@code EASY}, {@code MEDIUM}, {@code HARD} o {@code null} in base a {@code difficulty}.
     */
    public static @Nullable Difficulty fromString(String difficulty) {
        if (difficulty == null) return null;
        switch (difficulty.toLowerCase()) {
            case "facile", "easy" -> {
                return EASY;
            }
            case "intermedio", "medium" -> {
                return MEDIUM;
            }
            case "avanzato", "hard" -> {
                return HARD;
            }
            default -> {
                return null;
            }
        }
    }
}
