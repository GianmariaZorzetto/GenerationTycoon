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
     * Metodo per inizializzare un {@link KaboomBuilder}.
     *
     * @return un nuovo {@code KaboomBuilder}.
     */
    public static KaboomBuilder builder() {
        return new KaboomBuilder();
    }

    /**
     * Costruttore vuoto della classe.
     */
    public Kaboom() {
    }

    /**
     * Costruttore della classe.
     *
     * @param question     la domanda in ingresso.
     * @param answer1      la prima risposta.
     * @param answer2      la seconda risposta.
     * @param answer3      la terza risposta.
     * @param answer4      la quarta risposta.
     * @param correctColor il colore della riposta corretta.
     */
    private Kaboom(String question, String answer1, String answer2, String answer3, String answer4, KaboomColors correctColor) {
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.correctColor = correctColor;
    }

    /**
     * Costruttore completo della classe.
     *
     * @param id           l'id in ingresso.
     * @param question     la domanda in ingresso.
     * @param answer1      la prima risposta.
     * @param answer2      la seconda risposta.
     * @param answer3      la terza risposta.
     * @param answer4      la quarta risposta.
     * @param correctColor il colore della riposta corretta.
     * @throws IllegalArgumentException se {@code id} è minore di 1.
     * @throws NullPointerException     se {@code id} è {@code null}.
     */
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
     * @param question la nuova domanda in ingresso.
     * @throws IllegalArgumentException se {@code question} è {@code blank}.
     * @throws NullPointerException     se {@code question} è {@code null}.
     */
    public void setQuestion(String question) throws IllegalArgumentException, NullPointerException {
        if (Objects.requireNonNull(question, "La domanda non può essere null.").isBlank())
            throw new IllegalArgumentException("La domanda non può essere vuota");
        this.question = question;
    }

    /**
     * Getter di {@code answer1}.
     *
     * @return {@code answer1}.
     */
    public String getAnswer1() {
        return answer1;
    }

    /**
     * Setter di {@code answer1}.
     *
     * @param answer1 la nuova risposta 1.
     * @throws IllegalArgumentException se {@code answer1} è {@code blank}.
     * @throws NullPointerException     se {@code answer1} è {@code null}.
     */
    public void setAnswer1(String answer1) throws IllegalArgumentException, NullPointerException {
        if (Objects.requireNonNull(answer1, "Risposta non può essere null.").isBlank())
            throw new IllegalArgumentException("La risposta uno non può essere vuota");
        this.answer1 = answer1;
    }

    /**
     * Getter di {@code answer2}.
     *
     * @return {@code answer2}.
     */
    public String getAnswer2() {
        return answer2;
    }

    /**
     * Setter di {@code answer2}.
     *
     * @param answer2 la nuova risposta 2.
     * @throws IllegalArgumentException se {@code answer2} è {@code blank}.
     * @throws NullPointerException     se {@code answer2} è {@code null}.
     */
    public void setAnswer2(String answer2) throws IllegalArgumentException, NullPointerException {
        if (Objects.requireNonNull(answer2, "Risposta non può essere null.").isBlank())
            throw new IllegalArgumentException("La risposta due non può essere vuota");
        this.answer2 = answer2;
    }

    /**
     * Getter di {@code answer3}.
     *
     * @return {@code answer3}.
     */
    public String getAnswer3() {
        return answer3;
    }

    /**
     * Setter di {@code answer3}.
     *
     * @param answer3 la nuova risposta 3.
     * @throws IllegalArgumentException se {@code answer3} è {@code blank}.
     * @throws NullPointerException     se {@code answer3} è {@code null}.
     */
    public void setAnswer3(String answer3) throws IllegalArgumentException, NullPointerException {
        if (Objects.requireNonNull(answer3, "Risposta non può essere null.").isBlank())
            throw new IllegalArgumentException("La risposta tre non può essere vuota");
        this.answer3 = answer3;
    }

    /**
     * Getter di {@code answer4}.
     *
     * @return {@code answer4}.
     */
    public String getAnswer4() {
        return answer4;
    }

    /**
     * Setter di {@code answer4}.
     *
     * @param answer4 la nuova risposta 4.
     * @throws IllegalArgumentException se {@code answer4} è {@code blank}.
     * @throws NullPointerException     se {@code answer4} è {@code null}.
     */
    public void setAnswer4(String answer4) throws IllegalArgumentException, NullPointerException {
        if (Objects.requireNonNull(answer4, "Risposta non può essere null.").isBlank())
            throw new IllegalArgumentException("La risposta quattro non può essere vuota");
        this.answer4 = answer4;
    }

    /**
     * Getter di {@code correctColor}.
     *
     * @return {@code correctColor}.
     */
    public KaboomColors getCorrectColor() {
        return correctColor;
    }


    /**
     * Setter di {@code correctColor}.
     *
     * @param correctColor il nuovo colore della risposta corretta.
     * @throws NullPointerException se {@code correctColor} è {@code null}.
     */
    public void setCorrectColor(KaboomColors correctColor) throws NullPointerException {
        this.correctColor = Objects.requireNonNull(correctColor, "Impossibile impostare il colore come null.");
    }

    /**
     * Metodo che verifica se il colore scelto corrisponde alla risposta corretta.
     *
     * @param color il colore scelto.
     * @return {@code true} se la risposta scelta è quella corretta, {@code false} altrimenti.
     */
    public boolean isCorrectAnswer(KaboomColors color) {
        return correctColor.equals(color);
    }


    /**
     * Classe che aderisce al pattern <em>builder</em> per la costruzione di {@code Kaboom}.
     */
    public static class KaboomBuilder {
        /**
         * L'id.
         */
        private Long id;
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
         * Costruttore privato. Per inizializzare un builder utilizzare {@link Kaboom#builder}
         */
        private KaboomBuilder() {
            this.question = null;
            this.answer1 = null;
            this.answer2 = null;
            this.answer3 = null;
            this.answer4 = null;
            this.correctColor = null;
            this.id = null;
        }

        /**
         * Imposta la domanda.
         *
         * @param question la domanda in ingresso.
         * @return {@code this}.
         * @throws NullPointerException     se {@code question} è {@code null}.
         * @throws IllegalArgumentException se {{@code question} è {@code blank}.
         */
        public KaboomBuilder question(String question) throws NullPointerException, IllegalArgumentException {
            if (Objects.requireNonNull(question, "La domanda non può essere null.").isBlank())
                throw new IllegalArgumentException("La domanda non può essere vuota");
            this.question = question;
            return this;
        }

        /**
         * Imposta la prima risposta.
         *
         * @param answer1 la prima risposta in ingresso.
         * @return {@code this}.
         * @throws NullPointerException     se {@code answer1} è {@code null}.
         * @throws IllegalArgumentException se {@code answer1} è {@code blank}.
         */
        public KaboomBuilder answer1(String answer1) throws NullPointerException, IllegalArgumentException {
            if (Objects.requireNonNull(answer1, "Risposta non può essere null.").isBlank())
                throw new IllegalArgumentException("La risposta uno non può essere vuota");
            this.answer1 = answer1;
            return this;
        }

        /**
         * Imposta la seconda risposta.
         *
         * @param answer2 la seconda risposta in ingresso.
         * @return {@code this}.
         * @throws IllegalArgumentException se {@code answer2} è {@code blank}.
         * @throws NullPointerException     se {@code answer2} è {@code null}.
         */
        public KaboomBuilder answer2(String answer2) throws NullPointerException, IllegalArgumentException {
            if (Objects.requireNonNull(answer2, "Risposta non può essere null.").isBlank())
                throw new IllegalArgumentException("La risposta due non può essere vuota");
            this.answer2 = answer2;
            return this;
        }

        /**
         * Imposta la terza risposta.
         *
         * @param answer3 la terza risposta in ingresso.
         * @return {@code this}.
         * @throws NullPointerException     se {@code answer3} è {@code null}.
         * @throws IllegalArgumentException se {@code answer3} è {@code blank}.
         */
        public KaboomBuilder answer3(String answer3) throws NullPointerException, IllegalArgumentException {
            if (Objects.requireNonNull(answer3, "Risposta non può essere null.").isBlank())
                throw new IllegalArgumentException("La risposta tre non può essere vuota");
            this.answer3 = answer3;
            return this;
        }

        /**
         * Imposta la quarta risposta.
         *
         * @param answer4 la quarta risposta in ingresso.
         * @return {@code this}.
         * @throws NullPointerException     se {@code answer4} è {@code null}.
         * @throws IllegalArgumentException se {@code answer4} è {@code blank}.
         */
        public KaboomBuilder answer4(String answer4) throws NullPointerException, IllegalArgumentException {
            if (Objects.requireNonNull(answer4, "Risposta non può essere null.").isBlank())
                throw new IllegalArgumentException("La risposta quattro non può essere vuota");
            this.answer4 = answer4;
            return this;
        }

        /**
         * Imposta il colore della risposta corretta.
         *
         * @param correctColor il colore in ingresso.
         * @return {@code this.}
         * @throws NullPointerException se {@code correctColor} è {@code null}.
         */
        public KaboomBuilder correctColor(KaboomColors correctColor) throws NullPointerException {
            this.correctColor = Objects.requireNonNull(correctColor, "Impossibile impostare il colore come null.");
            return this;
        }

        /**
         * Imposta l'id.
         *
         * @param id l'id in ingresso.
         * @return {@code this}.
         * @throws NullPointerException     se {@code id} è {@code null}.
         * @throws IllegalArgumentException se{@code id} è minore di {@code 1}.
         */
        public KaboomBuilder id(Long id) throws NullPointerException, IllegalArgumentException {
            if (Objects.requireNonNull(id, "Id nullo.") < 1)
                throw new IllegalArgumentException("Id negativo.");
            this.id = id;
            return this;
        }

        /**
         * Completa la costruzione di un {@link Kaboom}.
         *
         * @return un nuovo {@code Kaboom} con i campi impostati dai vari metodi.
         */
        public Kaboom build() {
            if (id == null)
                return new Kaboom(question, answer1, answer2, answer3, answer4, correctColor);
            else return new Kaboom(id, question, answer1, answer2, answer3, answer4, correctColor);
        }
    }
}
