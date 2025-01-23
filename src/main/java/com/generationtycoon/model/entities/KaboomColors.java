package com.generationtycoon.model.entities;

import org.springframework.lang.Nullable;

/**
 * {@link Enum} che rappresenta i colori delle possibili risposte di un kahoot.</br>
 */
public enum KaboomColors {
    /**
     * Colore rosso.
     */
    RED,
    /**
     * Colore blu.
     */
    BLUE,
    /**
     * Colore verde.
     */
    GREEN,
    /**
     * Colore giallo.
     */
    YELLOW,
    ;

    /**
     * Metodo che restituisce un colore in base alla stringa in ingresso:
     * <ul>
     * <li>{@code coloreSupportato} -&gt; un {@code KaboomColors}.</li>
     * <li>{@code qualsiasiAltraStringa} -&gt; {@code null}.</li>
     * </ul>
     *
     * @param color la stringa in ingresso.
     * @return un {@code KaboomColors} o {@code null}.
     */
    public static @Nullable KaboomColors fromString(String color) {
        if (color == null) return null;
        switch (color.toLowerCase()) {
            case "rosso", "red" -> {
                return KaboomColors.RED;
            }
            case "blue", "blu" -> {
                return KaboomColors.BLUE;
            }
            case "green", "verde" -> {
                return KaboomColors.GREEN;
            }
            case "yellow", "giallo" -> {
                return KaboomColors.YELLOW;
            }
            default -> {
                return null;
            }
        }
    }
}
