package com.generationtycoon.controllers;

import com.generationtycoon.controllers.helpers.ControllerHelper;
import com.generationtycoon.model.dto.KaboomRespDto;
import com.generationtycoon.utils.credentials.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/kabooms")
public class KaboomController {

    private final ControllerHelper ch;
    private final CredentialService service;

    @Autowired
    public KaboomController(ControllerHelper ch, CredentialService service) {
        this.ch = ch;
        this.service = service;
    }

    @GetMapping
    public List<KaboomRespDto> getAll() {
        service.getUserByToken();
        return ch.getAllKabooms();
    }

    @GetMapping("/{id}")
    public KaboomRespDto getOne(@PathVariable Long id) {
        service.getUserByToken();
        return ch.getKaboomById(id);
    }
}
