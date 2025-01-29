package com.generationtycoon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principale dell'applicazione Generation Tycoon.
 * <p>
 * Questa classe avvia l'applicazione Spring Boot utilizzando il metodo
 * {@link SpringApplication#run(Class, String...)}.
 * </p>
 * <p>
 * L'annotazione {@code @SpringBootApplication} abilita la configurazione automatica,
 * la scansione dei componenti e permette di definire le configurazioni personalizzate.
 * </p>
 */
@SpringBootApplication
public class GenerationTycoonApplication {

    /**
     * Metodo principale per avviare l'applicazione Generation Tycoon.
     *
     * @param args argomenti della riga di comando passati all'applicazione.
     */
    public static void main(String[] args) {
        SpringApplication.run(GenerationTycoonApplication.class, args);
    }

}
