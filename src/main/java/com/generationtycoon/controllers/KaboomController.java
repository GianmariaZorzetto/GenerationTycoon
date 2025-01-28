package com.generationtycoon.controllers;

import com.generationtycoon.controllers.helpers.ControllerHelper;
import com.generationtycoon.model.dto.BrainjRespDto;
import com.generationtycoon.model.dto.KaboomRespDto;
import com.generationtycoon.utils.credentials.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller REST per la gestione delle risorse "Kaboom".
 * Offre endpoint per ottenere tutti i Kaboom o un Kaboom specifico tramite il suo ID.
 */
@RestController
@RequestMapping("api/kabooms")
public class KaboomController {

    private final ControllerHelper ch;
    private final CredentialService service;

    /**
     * Costruttore per l'iniezione delle dipendenze.
     *
     * @param ch      il {@link ControllerHelper} per eseguire le operazioni logiche.
     * @param service il {@link CredentialService} per la gestione delle credenziali.
     */
    @Autowired
    public KaboomController(ControllerHelper ch, CredentialService service) {
        this.ch = ch;
        this.service = service;
    }

    /**
     * Endpoint per ottenere la lista di tutti i quiz di tipo "kaboom".
     * Questo metodo verifica prima il token dell'utente tramite il {@link CredentialService}.
     *
     * @return una lista di {@link KaboomRespDto} che rappresenta tutti i quiz disponibili.
     */
    @GetMapping
    public List<KaboomRespDto> getAll() {
        service.getUserByToken();
        return ch.getAllKabooms();
    }

    /**
     * Endpoint per ottenere un quiz di tipo "kaboom" specifico dato il suo ID.
     * Questo metodo verifica prima il token dell'utente tramite il {@link CredentialService}.
     *
     * @param id l'ID del quiz da cercare.
     * @return un {@link KaboomRespDto} che rappresenta il quiz corrispondente all'ID fornito.
     * @throws com.generationtycoon.controllers.exceptions.KaboomMissingException se il quiz con l'ID specificato non esiste.
     */
    @GetMapping("/{id}")
    public KaboomRespDto getOne(@PathVariable Long id) {
        service.getUserByToken();
        return ch.getKaboomById(id);
    }
}
