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
public record UserLoginRespDto(String token, Long id, String username, Difficulty difficulty) {

    public static UserLoginRespDtoBuilder builder() {
        return new UserLoginRespDtoBuilder();
    }

    public static class UserLoginRespDtoBuilder {
        private String token;
        private Long id;
        private String username;
        private Difficulty difficulty;

        private UserLoginRespDtoBuilder() {
        }

        public UserLoginRespDtoBuilder token(String token) {
            this.token = token;
            return this;
        }

        public UserLoginRespDtoBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserLoginRespDtoBuilder difficulty(Difficulty difficulty) {
            this.difficulty = difficulty;
            return this;
        }

        public UserLoginRespDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserLoginRespDto build() {
            return new UserLoginRespDto(token, id, username, difficulty);
        }
    }
}
