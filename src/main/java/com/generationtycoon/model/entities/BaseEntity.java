package com.generationtycoon.model.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.util.Objects;

/**
 * Classe <strong>astratta</strong> che rappresenta una {@code BaseEntity}.
 * Una <em>BaseEntity</em> possiede un {@code ID}.
 */
@MappedSuperclass
public abstract class BaseEntity {
    /**
     * Id della classe
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Costruttore vuoto della classe.
     */
    protected BaseEntity() {
    }

    /**
     * Costruttore della classe.
     *
     * @param id l'id da assegnare all'oggetto.
     * @throws IllegalArgumentException se {@code id} è minore di {@code 1}.
     * @throws NullPointerException     se {@code id} è {@code null}.
     */
    protected BaseEntity(Long id) throws IllegalArgumentException, NullPointerException {
        if (Objects.requireNonNull(id, "Id non può essere null.") < 1)
            throw new IllegalArgumentException("Id non può essere minore di 1");
        this.id = id;
    }

    /**
     * Getter di {@code id}.
     *
     * @return {@code id}
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter di {@code id}.
     *
     * @param id il nuovo id da impostare.
     * @throws IllegalArgumentException se {@code id} è minore di {@code 1}.
     * @throws NullPointerException     se {@code id} è {@code null}.
     */
    public void setId(Long id) throws IllegalArgumentException, NullPointerException {
        if (Objects.requireNonNull(id, "Id non può essere null.") < 1)
            throw new IllegalArgumentException("Id non può essere minore di 1");
        this.id = id;
    }
}
