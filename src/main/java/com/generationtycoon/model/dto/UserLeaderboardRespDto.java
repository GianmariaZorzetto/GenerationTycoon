package com.generationtycoon.model.dto;

import com.generationtycoon.model.entities.Difficulty;

/**
 * Dto per la risposta a una richiesta di leaderboard.
 * Il dto è una classe <strong>immutabile</strong> questo significa che una volta finalizzata la costruzione i suoi parametri non possono essere modificati.
 * Si consiglia l'utilizzo della classe builder per la creazione o modifica dei parametri.
 *
 * @param username   lo username dello user.
 * @param difficulty la difficoltà selezionata dallo user.
 * @param score      lo score dello user.
 */
public record UserLeaderboardRespDto(String username, Difficulty difficulty, double score) {


    /**
     * Metodo per l'inizializzazione di un {@link UserLeaderboardRespDtoBuilder}.
     *
     * @return un nuovo {@code UserLeaderboardRespDtoBuilder}.
     */
    public static UserLeaderboardRespDtoBuilder builder() {
        return new UserLeaderboardRespDtoBuilder();
    }

    /**
     * Classe che aderisce al pattern <em>builder</em> per la costruzione di {@code UserLeaderboardRespDto}.
     */
    public static class UserLeaderboardRespDtoBuilder {
        /**
         * Lo username.
         */
        private String username;
        /**
         * La difficoltà.
         */
        private Difficulty difficulty;
        /**
         * Il punteggio.
         */
        private double score;

        /**
         * Costruttore privato e vuoto di un {@code UserLeaderboardRespDtoBuilder}.
         * Per inizializzare utilizzare {@link UserLeaderboardRespDto#builder}
         */
        private UserLeaderboardRespDtoBuilder() {
        }

        /**
         * Imposta lo username.
         *
         * @param username lo username.
         * @return {@code this}.
         */
        public UserLeaderboardRespDtoBuilder username(String username) {
            this.username = username;
            return this;
        }

        /**
         * Imposta la difficoltà.
         *
         * @param difficulty la difficoltà come {@link Difficulty}.
         * @return {@code this}.
         */
        public UserLeaderboardRespDtoBuilder difficulty(Difficulty difficulty) {
            this.difficulty = difficulty;
            return this;
        }

        /**
         * Imposta la difficoltà.
         *
         * @param difficulty la difficoltà come {@link String}.
         * @return {@code this}.
         */
        public UserLeaderboardRespDtoBuilder difficultyFromString(String difficulty) {
            this.difficulty = Difficulty.fromString(difficulty);
            return this;
        }

        /**
         * Imposta lo score.
         *
         * @param score imposta lo score.
         * @return {@code this}.
         */
        public UserLeaderboardRespDtoBuilder score(double score) {
            this.score = score;
            return this;
        }

        /**
         * Metodo per finalizzare la costruzione dell'oggetto {@link UserLeaderboardRespDto}
         *
         * @return un nuovo {@link UserLeaderboardRespDto} con i campi impostati dai metodi chiamati in precedenza.
         */
        public UserLeaderboardRespDto build() {
            return new UserLeaderboardRespDto(username, difficulty, score);
        }
    }
}
