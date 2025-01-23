package com.generationtycoon.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.generationtycoon.model.entities.KaboomColors;

/**
 * Dto di un {@link com.generationtycoon.model.entities.Kaboom} richiesta dal client.
 *
 * @param question     la domanda del {@code Kaboom}.
 * @param answer1      la prima risposta possibile.
 * @param answer2      la seconda risposta possibile.
 * @param answer3      la terza risposta possibile.
 * @param answer4      la quarta risposta possibile.
 * @param correctColor il colore della risposta corretta.
 */
@JsonSerialize
@JsonDeserialize(as = KaboomRespDto.class)
public record KaboomRespDto(String question,
                            String answer1,
                            String answer2,
                            String answer3,
                            String answer4,
                            String correctColor) {

    /**
     * Metodo per inizializzare un builder.
     *
     * @return un nuovo {@code KaboomRespDtoBuilder}.
     */
    public static KaboomRespDtoBuilder builder() {
        return new KaboomRespDtoBuilder();
    }

    /**
     * Classe che aderisce al pattern <em>builder</em> per la costruzione di {@code KaboomRespDto}.
     */
    public static class KaboomRespDtoBuilder {
        /**
         * La domanda.
         */
        private String question;
        /**
         * La prima risposta.
         */
        private String answer1;
        /**
         * La seconda risposta.
         */
        private String answer2;
        /**
         * La terza risposta.
         */
        private String answer3;
        /**
         * La quarta risposta.
         */
        private String answer4;
        /**
         * Il colore della risposta corretta.
         */
        private KaboomColors correctColor;

        /**
         * Costruttore privato della classe; per inizializzare un builder utilizzare il metodo {@link KaboomRespDto#builder}
         */
        private KaboomRespDtoBuilder() {
        }

        /**
         * Imposta la domanda.
         *
         * @param question la domanda in ingresso.
         * @return {@code this}.
         */
        public KaboomRespDtoBuilder question(String question) {
            this.question = question;
            return this;
        }

        /**
         * Imposta la prima risposta.
         *
         * @param answer1 la prima risposta in ingresso.
         * @return {@code this}.
         */
        public KaboomRespDtoBuilder answer1(String answer1) {
            this.answer1 = answer1;
            return this;
        }

        /**
         * Imposta la seconda riposta.
         *
         * @param answer2 la seconda risposta in ingresso.
         * @return {@code this}.
         */
        public KaboomRespDtoBuilder answer2(String answer2) {
            this.answer2 = answer2;
            return this;
        }

        /**
         * Imposta la terza risposta.
         *
         * @param answer3 la terza risposta in ingresso.
         * @return {@code this}.
         */
        public KaboomRespDtoBuilder answer3(String answer3) {
            this.answer3 = answer3;
            return this;
        }

        /**
         * Imposta la quarta risposta.
         *
         * @param answer4 la quarta risposta in ingresso.
         * @return {@code this}.
         */
        public KaboomRespDtoBuilder answer4(String answer4) {
            this.answer4 = answer4;
            return this;
        }

        /**
         * Imposta il colore della risposta corretta.
         *
         * @param correctColor il colore in ingresso.
         * @return {@code this}.
         */
        public KaboomRespDtoBuilder correctColor(KaboomColors correctColor) {
            this.correctColor = correctColor;
            return this;
        }

        /**
         * Completa la costruzione di un {@link KaboomRespDto}.
         *
         * @return un nuovo {@code KaboomRespDto} con i campi impostati dai metodi chiamati in precedenza.
         */
        public KaboomRespDto build() {
            return new KaboomRespDto(question, answer1, answer2, answer3, answer4, correctColor.name().toLowerCase());
        }

    }

}
