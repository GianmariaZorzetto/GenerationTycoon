package com.generationtycoon;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Classe di inizializzazione del servlet per l'applicazione Generation Tycoon.
 * <p>
 * Questa classe estende {@link SpringBootServletInitializer} per configurare
 * l'applicazione come un'applicazione WAR in un container servlet, come Apache Tomcat.
 * </p>
 */
public class ServletInitializer extends SpringBootServletInitializer {

    /**
     * Configura l'applicazione Spring Boot per il contesto servlet.
     * <p>
     * Specifica la classe principale dell'applicazione,
     * {@link GenerationTycoonApplication}, come sorgente dell'applicazione.
     * </p>
     *
     * @param application l'istanza di {@link SpringApplicationBuilder} da configurare.
     * @return l'istanza di {@link SpringApplicationBuilder} configurata.
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(GenerationTycoonApplication.class);
    }

}
