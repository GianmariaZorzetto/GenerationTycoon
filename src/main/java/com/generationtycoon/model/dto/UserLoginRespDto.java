package com.generationtycoon.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.generationtycoon.model.entities.Difficulty;

/**
 * Dto per la risposta a un login.
 *
 * @param token    il token dell'user <em>loggato</em>.
 * @param id       l'id dell'utente.
 * @param username lo username dell'utente.
 */
@JsonSerialize
@JsonDeserialize(as = UserLoginRespDto.class)
public record UserLoginRespDto(
        String token,
        Long id,
        String username,
        Double score,
        Difficulty difficulty
) {

    /**
     * Crea un nuovo builder per costruire un'istanza di {@link UserLoginRespDto}.
     *
     * @return una nuova istanza di {@link UserLoginRespDtoBuilder}.
     */
    public static UserLoginRespDtoBuilder builder() {
        return new UserLoginRespDtoBuilder();
    }

    /**
     * Classe builder per costruire oggetti {@link UserLoginRespDto}.
     */
    public static class UserLoginRespDtoBuilder {
        private String token;
        private Long id;
        private String username;
        private Difficulty difficulty;
        private Double score;

        /**
         * Costruttore privato del builder.
         */
        private UserLoginRespDtoBuilder() {
        }

        /**
         * Imposta il token dell'utente.
         *
         * @param token il token di autenticazione dell'utente.
         */
        public UserLoginRespDtoBuilder token(String token) {
            this.token = token;
            return this;
        }

        /**
         * Imposta lo username dell'utente.
         *
         * @param username lo username dell'utente.
         */
        public UserLoginRespDtoBuilder username(String username) {
            this.username = username;
            return this;
        }

        /**
         * Imposta il livello di difficoltà dell'utente.
         *
         * @param difficulty il livello di difficoltà associato all'utente.
         */
        public UserLoginRespDtoBuilder difficulty(Difficulty difficulty) {
            this.difficulty = difficulty;
            return this;
        }

        /**
         * Imposta l'ID dell'utente.
         *
         * @param id l'identificativo univoco dell'utente.
         */
        public UserLoginRespDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        /**
         * Imposta il punteggio dell'utente.
         *
         * @param score il punteggio corrente dell'utente.
         */
        public UserLoginRespDtoBuilder score(Double score) {
            this.score = score;
            return this;
        }

        /**
         * Crea un'istanza di {@link UserLoginRespDto} con i valori specificati.
         *
         * @return una nuova istanza di {@link UserLoginRespDto}.
         */
        public UserLoginRespDto build() {
            return new UserLoginRespDto(token, id, username, score, difficulty);
        }
    }
}
