package com.generationtycoon.model.dto;

public record BrainjRespDto(String question, String answer) {

    public static BrainjRespDtoBuilder builder() {
        return new BrainjRespDtoBuilder();
    }

    public static class BrainjRespDtoBuilder {

        private String question;

        private String answer;


        private BrainjRespDtoBuilder() {
        }

        public BrainjRespDtoBuilder question(String question) {
            this.question = question;
            return this;
        }

        public BrainjRespDtoBuilder answer(String answer) {
            this.answer = answer;
            return this;
        }

        public BrainjRespDto build() {
            return new BrainjRespDto(question, answer);
        }

    }

}
