package com.generationtycoon.model.repositories;

import com.generationtycoon.model.entities.Kaboom;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository di {@link Kaboom}
 */
public interface KaboomRepository extends JpaRepository<Kaboom, Long> {
}
