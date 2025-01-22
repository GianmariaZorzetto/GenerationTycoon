package com.generationtycoon.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class Brainj extends BaseEntity {
    @Column(nullable = false)
    private String question;

    @Column(nullable = false)
    private String answer;

    public Brainj() {
    }

    private Brainj(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    private Brainj(Long id, String question, String answer) throws IllegalArgumentException, NullPointerException {
        super(id);
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) throws NullPointerException, IllegalArgumentException {
        if (Objects.requireNonNull(question, "Domanda null.").isBlank())
            throw new IllegalArgumentException("Domanda vuoto.");
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) throws NullPointerException, IllegalArgumentException {
        if (Objects.requireNonNull(answer, "Risposta null.").isBlank())
            throw new IllegalArgumentException("Risposta vuota.");
        this.answer = answer;
    }

    public static BrainjBuilder builder() {
        return new BrainjBuilder();
    }

    public static class BrainjBuilder {
        private String question;
        private String answer;
        private Long id;

        private BrainjBuilder() {
            question = null;
            answer = null;
            id = null;
        }

        public BrainjBuilder question(String question) {
            if (Objects.requireNonNull(question, "Domanda null.").isBlank())
                throw new IllegalArgumentException("Domanda vuota.");
            this.question = question;
            return this;
        }

        public BrainjBuilder answer(String answer) {
            if (Objects.requireNonNull(answer, "Risposta null.").isBlank())
                throw new IllegalArgumentException("Risposta vuota.");
            this.answer = answer;
            return this;
        }

        public BrainjBuilder id(Long id) {
            if (Objects.requireNonNull(id, "Id null.") < 1)
                throw new IllegalArgumentException("Id vuota.");
            this.id = id;
            return this;
        }

        public Brainj build() {
            if (id == null) return new Brainj(question, answer);
            return new Brainj(id, question, answer);
        }
    }
}
