package com.generationtycoon.model.entities;

import org.springframework.lang.Nullable;

/**
 * {@link Enum} che rappresenta i colori delle possibili risposte di un kahoot.</br>
 * I possibili colori sono:
 * <ul>
 *     <li>{@code RED}</li>
 *     <li>{@code BLUE}</li>
 *     <li>{@code GREEN}</li>
 *     <li>{@code YELLOW}</li>
 * </ul>
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
     * <li>{@code rosso} o {@code red} -&gt; {@code RED}.</li>
     * <li>{@code blu} o {@code blue} -&gt; {@code BLUE}.</li>
     * <li>{@code green} o {@code verde} -&gt; {@code GREEN}.</li>
     * <li>{@code giallo} o {@code yellow} -&gt; {@code YELLOW}.</li>
     * <li>{@code qualsiasiAltraStringa} -&gt; {@code null}.</li>
     * </ul>
     *
     * @param color la stringa in ingresso.
     * @return {@code RED}, {@code YELLOW}, {@code BLUE}, {@code GREEN} o {@code null}.
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
