package com.generationtycoon.model.repositories;

import com.generationtycoon.model.entities.Difficulty;
import com.generationtycoon.model.entities.UserTycoon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository di {@link UserTycoon}.
 */
public interface UserRepository extends JpaRepository<UserTycoon, Long> {
    /**
     * Cerca nel database un {@link UserTycoon} dato il suo {@code username}.
     *
     * @param username l'username da cercare.
     * @return un {@link Optional} che potrebbe contenere l'{@link UserTycoon} con username uguale a {@code username}.
     */
    Optional<UserTycoon> findByUsername(String username);

    /**
     * Cerca nel database un {@link UserTycoon} data la sua {@code email}.
     *
     * @param email l'email dello user da cercare.
     * @return un {@link Optional} che potrebbe contenere l'{@link UserTycoon} con email uguale a {@code email}.
     */
    Optional<UserTycoon> findByEmail(String email);

    /**
     * Cerca nel database un {@link UserTycoon} dato il suo username e la sua password <em>hashata</em>.
     *
     * @param username l'username dello {@link UserTycoon} da cercare.
     * @param password la password <em>hashata</em> dello {@link UserTycoon} da cercare.
     * @return un {@link Optional} che potrebbe contenere l'{@link UserTycoon} con username e password uguale a {@code username} e {@code password}.
     */
    Optional<UserTycoon> findByUsernameAndPassword(String username, String password);

    /**
     * Cerca nel database un {@link UserTycoon} dato il suo email e la sua password <em>hashata</em>.
     *
     * @param email    la email dello {@link UserTycoon} da cercare.
     * @param password la password <em>hashata</em> dello {@link UserTycoon} da cercare.
     * @return un {@link Optional} che potrebbe contenere l'{@link UserTycoon} con email e password uguale a {@code email} e {@code password}.
     */
    Optional<UserTycoon> findByEmailAndPassword(String email, String password);

    /**
     * Cerca nel database tutti gli {@link UserTycoon} con la stessa difficulty in ingresso.
     *
     * @param difficulty la difficulty da cercare.
     * @return una {@link List} con tutti gli {@link UserTycoon} con difficoltà uguale a {@code difficulty}.
     */
    List<UserTycoon> findByDifficulty(Difficulty difficulty);
}
