package com.generationtycoon.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.generationtycoon.model.entities.Difficulty;

/**
 * Dto per richiedere la registrazione di un nuovo utente:
 * nella richiesta arriveranno i parametri {@code email}, {@code username},
 * {@code password} e una {@code difficulty}.
 * Il dto è una classe <strong>immutabile</strong> questo significa che una volta finalizzata la costruzione i suoi parametri non possono essere modificati.
 * Si consiglia l'utilizzo della classe builder per la creazione o modifica dei parametri.
 */
@JsonSerialize
@JsonDeserialize(as = UserRegistrationReqDto.class)
public record UserRegistrationReqDto(
        String email,
        String username,
        String password,
        Difficulty difficulty
) {
    /**
     * @param email      l'email del nuovo utente.
     * @param username   lo username del nuovo utente.
     * @param password   la password del nuovo utente non <em>hashata</em>.
     * @param difficulty la difficoltà selezionata dall'utente.
     */
    public UserRegistrationReqDto {
    }

    /**
     * Metodo per inizializzare un {@link UserRegistrationReqDtoBuilder}
     *
     * @return un nuovo {@code UserRegistrationReqDtoBuilder}.
     */
    public static UserRegistrationReqDtoBuilder builder() {
        return new UserRegistrationReqDtoBuilder();
    }

    /**
     * Classe che aderisce al pattern <em>builder</em>, per la costruzione di un {@code UserRegistrationReqDto}.
     */
    public static class UserRegistrationReqDtoBuilder {
        /**
         * L'email del nostro {@link UserRegistrationReqDto}.
         */
        private String email;
        /**
         * Lo username del nostro {@link UserRegistrationReqDto}.
         */
        private String username;
        /**
         * La password in <strong>chiaro</strong> del nostro {@link UserRegistrationReqDto}.
         */
        private String password;
        /**
         * La difficoltà del nostro {@link UserRegistrationReqDto}.
         */
        private Difficulty difficulty;

        /**
         * Costruttore vuoto e privato.
         * Per inizializzare un UserRegistrationReqDtoBuilder utilizza {@link UserRegistrationReqDto#builder}.
         */
        private UserRegistrationReqDtoBuilder() {
        }

        /**
         * Metodo per impostare l'email
         *
         * @param email l'email in ingresso.
         * @return {@code this}.
         */
        public UserRegistrationReqDtoBuilder email(String email) {
            this.email = email;
            return this;
        }

        /**
         * Metodo per impostare lo username.
         *
         * @param username lo username in ingresso.
         * @return {@code this}.
         */
        public UserRegistrationReqDtoBuilder username(String username) {
            this.username = username;
            return this;
        }

        /**
         * Metodo per impostare la password.
         *
         * @param password la password in ingresso.
         * @return {@code this}.
         */
        public UserRegistrationReqDtoBuilder password(String password) {
            this.password = password;
            return this;
        }

        /**
         * Metodo per impostare la difficoltà.
         *
         * @param difficulty la difficoltà in ingresso.
         * @return {@code this}.
         */
        public UserRegistrationReqDtoBuilder difficulty(Difficulty difficulty) {
            this.difficulty = difficulty;
            return this;
        }

        /**
         * Metodo per impostare la difficoltà da una stringa in italiano.
         *
         * @param difficulty la difficoltà in ingresso come stringa.
         * @return {@code this}.
         */
        public UserRegistrationReqDtoBuilder difficultyFromString(String difficulty) {
            this.difficulty = Difficulty.fromString(difficulty);
            return this;
        }

        /**
         * Metodo per finalizzare la costruzione dell'oggetto {@link UserRegistrationReqDto}
         *
         * @return un nuovo {@link UserRegistrationReqDto} con i campi impostati dai metodi chiamati in precedenza.
         */
        public UserRegistrationReqDto build() {
            return new UserRegistrationReqDto(email, username, password, difficulty);
        }
    }
}
