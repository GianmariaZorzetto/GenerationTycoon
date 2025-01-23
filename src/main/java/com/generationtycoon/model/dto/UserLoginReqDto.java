package com.generationtycoon.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Dto per la richiesta di un login.
 * Il dto è una classe <strong>immutabile</strong> questo significa che una volta finalizzata la costruzione i suoi parametri non possono essere modificati.
 * Si consiglia l'utilizzo della classe builder per la creazione o modifica dei parametri.
 *
 * @param email    l'email dell'utente.
 * @param password la password dell'utente.
 */
@JsonSerialize
@JsonDeserialize(as = UserLoginReqDto.class)
public record UserLoginReqDto(String email, String password) {

    /**
     * Metodo per l'inizializzazione di un {@link UserLoginReqDtoBuilder}.
     *
     * @return un nuovo {@code UserLoginReqDtoBuilder}.
     */
    public static UserLoginReqDtoBuilder builder() {
        return new UserLoginReqDtoBuilder();
    }

    /**
     * Classe che aderisce al pattern <em>builder</em> per la costruzione {@code UserLoginReqDto}.
     */
    public static class UserLoginReqDtoBuilder {
        /**
         * Lo username del nostro {@link UserRegistrationReqDto}.
         */
        private String email;
        /**
         * La password dello user.
         */
        private String password;

        /**
         * Costruttore vuoto e privato.
         * Per inizializzare un UserLoginReqDtoBuilder.
         */
        private UserLoginReqDtoBuilder() {
        }

        /**
         * Metodo per impostare l'email
         *
         * @param email l'email in ingresso.
         * @return {@code this}.
         */
        public UserLoginReqDtoBuilder email(String email) {
            this.email = email;
            return this;
        }

        /**
         * Metodo per impostare la password.
         *
         * @param password la password in ingresso.
         * @return {@code this}.
         */
        public UserLoginReqDtoBuilder password(String password) {
            this.password = password;
            return this;
        }

        /**
         * Metodo per finalizzare la costruzione dell'oggetto {@link UserLoginReqDto}
         *
         * @return un nuovo {@link UserLoginReqDto} con i campi impostati dai metodi chiamati in precedenza.
         */
        public UserLoginReqDto build() {
            return new UserLoginReqDto(email, password);
        }

    }

}
