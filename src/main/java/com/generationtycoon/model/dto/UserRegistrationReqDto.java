package com.generationtycoon.model.dto;

import com.generationtycoon.model.entities.Difficulty;

public record UserRegistrationReqDto(String email, String username, String password,
                                     Difficulty difficulty) {

    public static UserRegistrationReqDtoBuilder of() {
        return new UserRegistrationReqDtoBuilder();
    }

    public static class UserRegistrationReqDtoBuilder {
        private String email;
        private String username;
        private String password;
        private Difficulty difficulty;

        private UserRegistrationReqDtoBuilder() {
        }

        public UserRegistrationReqDtoBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserRegistrationReqDtoBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserRegistrationReqDtoBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserRegistrationReqDtoBuilder difficulty(Difficulty difficulty) {
            this.difficulty = difficulty;
            return this;
        }

        public UserRegistrationReqDto build() {
            return new UserRegistrationReqDto(email, username, password, difficulty);
        }
    }
}
