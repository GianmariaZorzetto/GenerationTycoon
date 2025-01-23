package com.generationtycoon.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Dto per inviare un quiz di brainj.
 *
 * @param question la domanda del quiz.
 * @param answer   la risposta del quiz.
 */
@JsonSerialize
@JsonDeserialize(as = BrainjRespDto.class)
public record BrainjRespDto(String question, String answer) {

    /**
     * Metodo per istanziare un {@link BrainjRespDtoBuilder}.
     *
     * @return un nuovo {code BrainjRespoDtoBuilder}.
     */
    public static BrainjRespDtoBuilder builder() {
        return new BrainjRespDtoBuilder();
    }

    /**
     * Classe che aderisce al pattern <em>builder</em> per la costruzione di {@code BrainjRespDto}.
     */
    public static class BrainjRespDtoBuilder {

        /**
         * La domanda.
         */
        private String question;

        /**
         * La risposta.
         */
        private String answer;


        /**
         * Il costruttore.
         */
        private BrainjRespDtoBuilder() {
        }

        /**
         * Imposta la domanda.
         *
         * @param question la domanda.
         * @return {@code this}.
         */
        public BrainjRespDtoBuilder question(String question) {
            this.question = question;
            return this;
        }

        /**
         * Imposta la risposta.
         *
         * @param answer la risposta.
         * @return {@code this}.
         */
        public BrainjRespDtoBuilder answer(String answer) {
            this.answer = answer;
            return this;
        }

        /**
         * Metodo per finalizzare la costruzione dell'oggetto {@link BrainjRespDto}
         *
         * @return un nuovo {@code BrainjRespDto} con i campi impostati dai metodi chiamati in precedenza.
         */
        public BrainjRespDto build() {
            return new BrainjRespDto(question, answer);
        }

    }

}
