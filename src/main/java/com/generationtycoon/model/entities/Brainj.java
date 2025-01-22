package com.generationtycoon.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.Objects;

/**
 * Entità che rappresenta un quiz di {@code Brainj},
 * Un quiz ha una <strong>domanda</strong> e una <strong>risposta</strong>.
 */
@Entity
public class Brainj extends BaseEntity {
    /**
     * La domanda del quiz.
     */
    @Column(nullable = false)
    private String question;

    /**
     * La risposta del quiz.
     */
    @Column(nullable = false)
    private String answer;

    /**
     * Costruttore vuoto.
     */
    public Brainj() {
    }

    /**
     * Metodo per istanziare un {@link BrainjBuilder}.
     *
     * @return un nuovo {@code BrainjBuilder}.
     */
    public static BrainjBuilder builder() {
        return new BrainjBuilder();
    }

    /**
     * Costruttore della classe.
     *
     * @param question la domanda del quiz.
     * @param answer   la risposta del quiz.
     */
    private Brainj(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    /**
     * Costruttore completo della classe.
     *
     * @param id       l'id della classe.
     * @param question la domanda del quiz.
     * @param answer   la risposta del quiz.
     * @throws IllegalArgumentException se {@code id} è minore di {@code 1}.
     * @throws NullPointerException     se {@code id} è {@code null}.
     */
    private Brainj(Long id, String question, String answer) throws IllegalArgumentException, NullPointerException {
        super(id);
        this.question = question;
        this.answer = answer;
    }

    /**
     * Getter di {@code question}.
     *
     * @return {@code question}.
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Setter di {@code question}.
     *
     * @param question la domanda del quiz.
     * @throws NullPointerException     se {@code question} è {@code null}.
     * @throws IllegalArgumentException se {@code question} è {@code blank}.
     */
    public void setQuestion(String question) throws NullPointerException, IllegalArgumentException {
        if (Objects.requireNonNull(question, "Domanda null.").isBlank())
            throw new IllegalArgumentException("Domanda vuoto.");
        this.question = question;
    }

    /**
     * Getter di {@code answer}.
     *
     * @return {@code answer}.
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Setter di {@code answer}.
     *
     * @param answer la risposta del quiz.
     * @throws NullPointerException     se {@code answer} è {@code null}.
     * @throws IllegalArgumentException se {@code answer} è {@code blank}.
     */
    public void setAnswer(String answer) throws NullPointerException, IllegalArgumentException {
        if (Objects.requireNonNull(answer, "Risposta null.").isBlank())
            throw new IllegalArgumentException("Risposta vuota.");
        this.answer = answer;
    }

    /**
     * Classe che aderisce al pattern <em>builder</em> per la costruzione di {@code Brainj}.
     */
    public static class BrainjBuilder {
        /**
         * La domanda.
         */
        private String question;
        /**
         * La risposta.
         */
        private String answer;
        /**
         * L'id.
         */
        private Long id;

        /**
         * Costruttore del builder; per istanziare un nuovo builder utilizzare {@link Brainj#builder()}.
         */
        private BrainjBuilder() {
            question = null;
            answer = null;
            id = null;
        }

        /**
         * Imposta la domanda.
         *
         * @param question la domanda del quiz.
         * @return {@code this}.
         * @throws NullPointerException     se {@code question} è {@code null}.
         * @throws IllegalArgumentException se {@code question} è {@code blank}.
         */
        public BrainjBuilder question(String question) throws NullPointerException, IllegalArgumentException {
            if (Objects.requireNonNull(question, "Domanda null.").isBlank())
                throw new IllegalArgumentException("Domanda vuota.");
            this.question = question;
            return this;
        }

        /**
         * Imposta la risposta.
         *
         * @param answer la risposta del quiz.
         * @return {@code this}.
         * @throws NullPointerException     se {@code answer} è {@code null}.
         * @throws IllegalArgumentException se {@code answer} è {@code blank}.
         */
        public BrainjBuilder answer(String answer) throws NullPointerException, IllegalArgumentException {
            if (Objects.requireNonNull(answer, "Risposta null.").isBlank())
                throw new IllegalArgumentException("Risposta vuota.");
            this.answer = answer;
            return this;
        }

        /**
         * Imposta l'id.
         *
         * @param id l'id del quiz.
         * @return {@code this}.
         * @throws NullPointerException     se {@code id} è {@code null}.
         * @throws IllegalArgumentException se {@code id} è minore di {@code 1}.
         */
        public BrainjBuilder id(Long id) throws NullPointerException, IllegalArgumentException {
            if (Objects.requireNonNull(id, "Id null.") < 1)
                throw new IllegalArgumentException("Id vuota.");
            this.id = id;
            return this;
        }

        /**
         * Completa la costruzione di un {@link Brainj}.
         *
         * @return un nuovo {@code Brainj} con i campi impostati dai vari metodi.
         */
        public Brainj build() {
            if (id == null) return new Brainj(question, answer);
            return new Brainj(id, question, answer);
        }
    }
}
