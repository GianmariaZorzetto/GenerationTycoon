package com.generationtycoon.model.dto;

/**
 * Dto per la risposta a un login.
 *
 * @param token    il token dell'user <em>loggato</em>.
 * @param id       l'id dell'utente.
 * @param username lo username dell'utente.
 */
public record UserLoginRespDto(String token, Long id, String username) {

    public static UserLoginRespDtoBuilder builder() {
        return new UserLoginRespDtoBuilder();
    }

    public static class UserLoginRespDtoBuilder {
        private String token;
        private Long id;
        private String username;

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

        public UserLoginRespDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserLoginRespDto build() {
            return new UserLoginRespDto(token, id, username);
        }
    }
}
