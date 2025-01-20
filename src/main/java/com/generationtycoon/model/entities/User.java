package com.generationtycoon.model.entities;

import com.generationtycoon.validator.Validator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Objects;


/**
 * Entità che rappresenta un {@code User} del nostro sito.</br>
 * Un {@code User} ha:
 * <ul>
 *     <li>Una email che deve essere <strong>unica</strong>.
 *     Deve aderire allo schema elencato da {@code EMAIL} in {@link Validator}.</li>
 *     <li>Una password che deve aderire allo schema elencato da {@code PASSWORD} in {@link Validator}.</li>
 *     <li>Uno username che non deve essere {@code null}.</li>
 *     <li>Una {@link Difficulty}.</li>
 *     <li>Uno score non negativo.</li>
 * </ul>
 */
@Entity
public class User extends BaseEntity {
    /**
     * Email dello {@code User}: deve essere non {@code null} e aderire alla regex detta nel {@link Validator}.
     */
    @Column(unique = true, nullable = false)
    private String email;
    /**
     * Password dello {@code User}: deve essere non {@code null} e non vuota e aderire alla regex detta nel {@link Validator}.
     */
    @Column(nullable = false)
    private String password;
    /**
     * Username dello {@code User}: deve essere non {@code null} e non vuota.
     */
    @Column(nullable = false)
    private String username;
    /**
     * Difficoltà impostata dallo {@code User}: deve essere non {@code null}.
     */
    @Column(nullable = false)
    private Difficulty difficulty;
    /**
     * Score dello {@code User}: deve essere non {@code null} e non negativo.
     */
    @Column(nullable = false)
    private Double score;

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    /**
     * Costruttore vuoto della classe.
     */
    public User() {
    }

    /**
     * Costruttore della classe dato una <em>email</em>,
     * <em>password</em> in chiaro, <em>username</em>,
     * <em>difficulty</em> e uno <em>score</em>.
     *
     * @param email      l'email dello {@code User}.
     * @param password   la password dello {@code User}.
     * @param username   lo username dello {@code User}.
     * @param difficulty la difficoltà dello {@code User}.
     * @param score      lo score dello {@code User}.
     */
    private User(String email, String password, String username, Difficulty difficulty, Double score) {
        super();
        this.email = email;
        this.password = DigestUtils.md5Hex(password);
        this.username = username;
        this.difficulty = difficulty;
        this.score = score;
    }

    /**
     * Costruttore completo della classe.
     *
     * @param id         l'id dello {@code User}.
     * @param email      l'email dello {@code User}.
     * @param password   la password dello {@code User}, in chiaro.
     * @param username   lo username dello {@code User}.
     * @param difficulty la difficoltà dello {@code User}.
     * @param score      lo score dello {@code User}.
     * @throws IllegalArgumentException se {@code id} è minore di 1.
     * @throws NullPointerException     se {@code id} è {@code null}.
     */
    private User(Long id, String email, String password, String username, Difficulty difficulty, Double score)
            throws IllegalArgumentException, NullPointerException {
        super(id);
        this.email = email;
        this.password = DigestUtils.md5Hex(password);
        this.username = username;
        this.difficulty = difficulty;
        this.score = score;
    }

    /**
     * Getter della email
     *
     * @return {@code email}.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter dell'email.
     *
     * @param email l'email da impostare.
     * @throws IllegalArgumentException se {@code email} non è conforme.
     */
    public void setEmail(String email) throws IllegalArgumentException {
        if (Validator.EMAIL.validate(email))
            throw new IllegalArgumentException("Email non conforme.");
        this.email = email;
    }

    /**
     * Getter della password hashata.
     *
     * @return {@code password}.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter della password
     *
     * @param password nuova password dello {@code User}, in chiaro.
     * @throws IllegalArgumentException se {@code password} non è valida.
     */
    public void setPassword(String password) throws IllegalArgumentException {
        if (Validator.PASSWORD.validate(password))
            throw new IllegalArgumentException("Password non conforme.");
        this.password = DigestUtils.md5Hex(password);
    }

    /**
     * Getter di {@code username}.
     *
     * @return {@code username}.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter di {@code username}.
     *
     * @param username il nuovo username dello {@code User}.
     * @throws IllegalArgumentException se {@code username} è blank.
     * @throws NullPointerException     se {@code username} è {@code null}.
     */
    public void setUsername(String username) throws IllegalArgumentException, NullPointerException {
        if (Objects.requireNonNull(username, "Username non può essere null.").isBlank())
            throw new IllegalArgumentException("Username non può essere vuoto.");
        this.username = username;
    }

    /**
     * Getter {@code difficulty}.
     *
     * @return {@code difficulty}.
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Setter di {@code difficulty}.
     *
     * @param difficulty la nuova difficoltà dello {@code User}.
     * @throws NullPointerException se {@code difficulty} è {@code null}.
     */
    public void setDifficulty(Difficulty difficulty) throws NullPointerException {
        this.difficulty = Objects.requireNonNull(difficulty, "Difficoltà non può essere null.");
    }

    /**
     * Getter di {@code score}.
     *
     * @return {@code score}.
     */
    public Double getScore() {
        return score;
    }

    /**
     * Setter di {@code score}.
     *
     * @param score il nuovo score dello {@code User}.
     * @throws IllegalArgumentException se {@code score} è minore di 0.
     * @throws NullPointerException     se {@code score} è {@code null}.
     */
    public void setScore(Double score) throws IllegalArgumentException, NullPointerException {
        if (Objects.requireNonNull(score, "Score non può essere null.") < 0)
            throw new IllegalArgumentException("Score non può essere negativo.");
        this.score = score;
    }

    /**
     * Classe che aderisce al pattern <em>builder</em> per la costruzione di {@code User}.
     */
    public static class UserBuilder {
        /**
         * L'id.
         */
        private Long id;
        /**
         * L'email.
         */
        private String email;
        /**
         * La password.
         */
        private String password;
        /**
         * Lo username.
         */
        private String username;
        /**
         * La difficoltà.
         */
        private Difficulty difficulty;
        /**
         * Il punteggio.
         */
        private Double score;

        /**
         * Costruttore privato del builder, per istanziare un {@code UserBuilder} utilizzare il metodo {@link User#builder()}.
         */
        private UserBuilder() {
            this.email = null;
            this.password = null;
            this.username = null;
            this.difficulty = null;
            this.score = null;
            this.id = null;
        }

        /**
         * Imposta l'email.
         *
         * @param email l'email in ingresso.
         * @return {@code this}.
         */
        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        /**
         * Imposta la password in chiaro.
         *
         * @param password la password in ingresso.
         * @return {@code this}.
         */
        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        /**
         * Imposta lo username.
         *
         * @param username lo username in ingresso.
         * @return {@code this}.
         */
        public UserBuilder username(String username) {
            this.username = username;
            return this;
        }

        /**
         * Imposta la difficoltà.
         *
         * @param difficulty la difficoltà in ingresso.
         * @return {@code this}.
         */
        public UserBuilder difficulty(Difficulty difficulty) {
            this.difficulty = difficulty;
            return this;
        }

        /**
         * Imposta la difficoltà.
         *
         * @param difficulty la difficoltà in ingresso come {@link String}.
         * @return {@code this}.
         */
        public UserBuilder difficultyFromString(String difficulty) {
            this.difficulty = Difficulty.fromString(difficulty);
            return this;
        }

        /**
         * Imposta lo score.
         *
         * @param score lo score in ingresso.
         * @return {@code this}.
         */
        public UserBuilder score(Double score) {
            this.score = score;
            return this;
        }

        /**
         * Imposta l'id dello user.
         *
         * @param id l'id in ingresso.
         * @return {@code this}.
         */
        public UserBuilder id(Long id) {
            this.id = id;
            return this;
        }

        /**
         * Completa la costruzione di un {@code User}
         *
         * @return un nuovo {@code User} in base ai campi impostati in precedenza.
         */
        public User build() {
            if (id == null) return new User(email, password, username, difficulty, score);
            else return new User(id, email, password, username, difficulty, score);
        }
    }
}
