package com.generationtycoon.model.repositories;

import com.generationtycoon.model.entities.Difficulty;
import com.generationtycoon.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository di {@link User}.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Cerca nel database un {@link User} dato il suo {@code username}.
     *
     * @param username l'username da cercare.
     * @return un {@link Optional} che potrebbe contenere l'{@link User} con username uguale a {@code username}.
     */
    Optional<User> findByUsername(String username);

    /**
     * Cerca nel database un {@link User} data la sua {@code email}.
     *
     * @param email l'email dello user da cercare.
     * @return un {@link Optional} che potrebbe contenere l'{@link User} con email uguale a {@code email}.
     */
    Optional<User> findByEmail(String email);

    /**
     * Cerca nel database un {@link User} dato il suo username e la sua password <em>hashata</em>.
     *
     * @param username l'username dello {@link User} da cercare.
     * @param password la password <em>hashata</em> dello {@link User} da cercare.
     * @return un {@link Optional} che potrebbe contenere l'{@link User} con username e password uguale a {@code username} e {@code password}.
     */
    Optional<User> findByUsernameAndPassword(String username, String password);

    /**
     * Cerca nel database un {@link User} dato il suo email e la sua password <em>hashata</em>.
     *
     * @param email    la email dello {@link User} da cercare.
     * @param password la password <em>hashata</em> dello {@link User} da cercare.
     * @return un {@link Optional} che potrebbe contenere l'{@link User} con email e password uguale a {@code email} e {@code password}.
     */
    Optional<User> findByEmailAndPassword(String email, String password);

    /**
     * Cerca nel database tutti gli {@link User} con la stessa difficulty in ingresso.
     *
     * @param difficulty la difficulty da cercare.
     * @return una {@link List} con tutti gli {@link User} con difficoltà uguale a {@code difficulty}.
     */
    List<User> findByDifficulty(Difficulty difficulty);
}
