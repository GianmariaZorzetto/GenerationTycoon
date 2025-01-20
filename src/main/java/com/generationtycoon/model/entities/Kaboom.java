package com.generationtycoon.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.Objects;

/**
 * Entità che rappresenta un {@code Kaboom} del nostro sito.</br>
 * Un kaboom ha una <strong>domanda</strong>, <strong>4</strong> possibili risposte e una risposta corretta.
 */
@Entity
public class Kaboom extends BaseEntity {
    /**
     * La domanda del kaboom.
     */
    @Column(nullable = false)
    private String question;
    /**
     * Domanda uno.
     */
    @Column(nullable = false)
    private String answer1;
    /**
     * Domanda due.
     */
    @Column(nullable = false)
    private String answer2;
    /**
     * Domanda tre.
     */
    @Column(nullable = false)
    private String answer3;
    /**
     * Domanda quattro.
     */
    @Column(nullable = false)
    private String answer4;
    /**
     * Colore della risposta.
     */
    @Column(nullable = false)
    private KaboomColors correctColor;

    /**
     * Costruttore vuoto della classe.
     */
    public Kaboom() {
    }

    private Kaboom(String question, String answer1, String answer2, String answer3, String answer4, KaboomColors correctColor)
            throws IllegalArgumentException, NullPointerException {
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.correctColor = correctColor;
    }

    private Kaboom(Long id, String question, String answer1, String answer2, String answer3, String answer4, KaboomColors correctColor)
            throws IllegalArgumentException, NullPointerException {
        super(id);
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.correctColor = correctColor;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) throws IllegalArgumentException, NullPointerException {
        if (Objects.requireNonNull(question, "La domanda non può essere null.").isBlank())
            throw new IllegalArgumentException("La domanda non può essere vuota");
        this.question = question;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) throws IllegalArgumentException, NullPointerException {
        if (Objects.requireNonNull(answer1, "Risposta non può essere null.").isBlank())
            throw new IllegalArgumentException("La risposta uno non può essere vuota");
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) throws IllegalArgumentException, NullPointerException {
        if (Objects.requireNonNull(answer2, "Risposta non può essere null.").isBlank())
            throw new IllegalArgumentException("La risposta due non può essere vuota");
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) throws IllegalArgumentException, NullPointerException {
        if (Objects.requireNonNull(answer3, "Risposta non può essere null.").isBlank())
            throw new IllegalArgumentException("La risposta tre non può essere vuota");
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) throws IllegalArgumentException, NullPointerException {
        if (Objects.requireNonNull(answer4, "Risposta non può essere null.").isBlank())
            throw new IllegalArgumentException("La risposta quattro non può essere vuota");
        this.answer4 = answer4;
    }

    public KaboomColors getCorrectColor() {
        return correctColor;
    }

    public void setCorrectColor(KaboomColors correctColor) throws NullPointerException {
        this.correctColor = Objects.requireNonNull(correctColor, "Impossibile impostare il colore come null.");
    }

    public boolean isCorrectAnswer(KaboomColors color) {
        return correctColor.equals(color);
    }


    public static class KaboomBuilder {
        private String question;
        private String answer1;
        private String answer2;
        private String answer3;
        private String answer4;
        private KaboomColors correctColor;
        private Long id;

        private KaboomBuilder() {
            this.question = null;
            this.answer1 = null;
            this.answer2 = null;
            this.answer3 = null;
            this.answer4 = null;
            this.correctColor = null;
            this.id = null;
        }

        public KaboomBuilder question(String question) {
            this.question = question;
            return this;
        }

        public KaboomBuilder answer1(String answer1) {
            this.answer1 = answer1;
            return this;
        }

        public KaboomBuilder answer2(String answer2) {
            this.answer2 = answer2;
            return this;
        }

        public KaboomBuilder answer3(String answer3) {
            this.answer3 = answer3;
            return this;
        }

        public KaboomBuilder answer4(String answer4) {
            this.answer4 = answer4;
            return this;
        }

        public KaboomBuilder correctColor(KaboomColors correctColor) {
            this.correctColor = correctColor;
            return this;
        }

        public KaboomBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public Kaboom build() {
            if (id == null)
                return new Kaboom(question, answer1, answer2, answer3, answer4, correctColor);
            else return new Kaboom(id, question, answer1, answer2, answer3, answer4, correctColor);
        }
    }
}
