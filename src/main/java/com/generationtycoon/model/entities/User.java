package com.generationtycoon.model.entities;

import com.generationtycoon.validator.Validator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class User extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private Difficulty difficulty;
    @Column(nullable = false)
    private Double score;

    public User() {
    }

    public User(String email, String password, String username, Difficulty difficulty, Double score)
            throws IllegalArgumentException, NullPointerException {
        if (Validator.EMAIL.validate(email))
            throw new IllegalArgumentException("Email non conforme.");
        if (Validator.PASSWORD.validate(password))
            throw new IllegalArgumentException("Password non conforme.");
        if (Objects.requireNonNull(username, "Username non può essere null.").isBlank())
            throw new IllegalArgumentException("Username non può essere vuoto.");
        this.email = email;
        this.password = password;
        this.username = username;
        this.difficulty = Objects.requireNonNull(difficulty, "Difficoltà non può essere null.");
        if (Objects.requireNonNull(score, "Score non può essere null.") < 0)
            throw new IllegalArgumentException("Score non può essere negativo.");
        this.score = score;
    }

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
        this.password = password;
        this.username = username;
        this.difficulty = Objects.requireNonNull(difficulty, "Difficoltà non può essere null.");
        if (Objects.requireNonNull(score, "Score non può essere null.") < 0)
            throw new IllegalArgumentException("Score non può essere negativo.");
        this.score = score;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (Validator.EMAIL.validate(email))
            throw new IllegalArgumentException("Email non conforme.");
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (Validator.PASSWORD.validate(password))
            throw new IllegalArgumentException("Password non conforme.");
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (Objects.requireNonNull(username, "Username non può essere null.").isBlank())
            throw new IllegalArgumentException("Username non può essere vuoto.");
        this.username = username;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = Objects.requireNonNull(difficulty, "Difficoltà non può essere null.");
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        if (Objects.requireNonNull(score, "Score non può essere null.") < 0)
            throw new IllegalArgumentException("Score non può essere negativo.");
        this.score = score;
    }
}
