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
     * @throws IllegalArgumentException se uno dei parametri non è conforme.
     * @throws NullPointerException     se uno dei parametri è {@code null}.
     */
    public User(String email, String password, String username, Difficulty difficulty, Double score)
            throws IllegalArgumentException, NullPointerException {
        super();
        if (Validator.EMAIL.validate(email))
            throw new IllegalArgumentException("Email non conforme.");
        if (Validator.PASSWORD.validate(password))
            throw new IllegalArgumentException("Password non conforme.");
        if (Objects.requireNonNull(username, "Username non può essere null.").isBlank())
            throw new IllegalArgumentException("Username non può essere vuoto.");
        this.email = email;
        this.password = DigestUtils.md5Hex(password);
        this.username = username;
        this.difficulty = Objects.requireNonNull(difficulty, "Difficoltà non può essere null.");
        if (Objects.requireNonNull(score, "Score non può essere null.") < 0)
            throw new IllegalArgumentException("Score non può essere negativo.");
        this.score = score;
    }

    /**
     * Costruttore della classe dato l'{@code ID}.
     *
     * @param id         l'id dello {@code User}.
     * @param email      l'email dello {@code User}.
     * @param password   la password dello {@code User}, in chiaro.
     * @param username   lo username dello {@code User}.
     * @param difficulty la difficoltà dello {@code User}.
     * @param score      lo score dello {@code User}.
     * @throws IllegalArgumentException se uno dei parametri non è conforme.
     * @throws NullPointerException     se uno dei parametri è {@code null}.
     */
    public User(Long id, String email, String password, String username, Difficulty difficulty, Double score)
            throws IllegalArgumentException, NullPointerException {
        super(id);
        if (Validator.EMAIL.validate(email))
            throw new IllegalArgumentException("Email non conforme.");
        if (Validator.PASSWORD.validate(password))
            throw new IllegalArgumentException("Password non conforme.");
        if (Objects.requireNonNull(username, "Username non può essere null.").isBlank())
            throw new IllegalArgumentException("Username non può essere vuoto.");
        this.email = email;
        this.password = DigestUtils.md5Hex(password);
        this.username = username;
        this.difficulty = Objects.requireNonNull(difficulty, "Difficoltà non può essere null.");
        if (Objects.requireNonNull(score, "Score non può essere null.") < 0)
            throw new IllegalArgumentException("Score non può essere negativo.");
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
}
