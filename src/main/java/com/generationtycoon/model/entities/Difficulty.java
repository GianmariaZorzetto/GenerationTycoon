package com.generationtycoon.model.entities;

public enum Difficulty {
    EASY, MEDIUM, HARD;

    public static Difficulty fromString(String difficulty) {
        switch (difficulty.toLowerCase()) {
            case "facile" -> {
                return EASY;
            }
            case "intermedio" -> {
                return MEDIUM;
            }
            case "avanzato" -> {
                return HARD;
            }
            default -> {
                return null;
            }
        }
    }
}
