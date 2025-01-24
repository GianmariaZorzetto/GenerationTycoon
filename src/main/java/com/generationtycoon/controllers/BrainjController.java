package com.generationtycoon.controllers;

import com.generationtycoon.controllers.helpers.ControllerHelper;
import com.generationtycoon.model.dto.BrainjRespDto;
import com.generationtycoon.utils.credentials.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/brainjs")
public class BrainjController {

    private final ControllerHelper ch;
    private final CredentialService service;

    @Autowired
    public BrainjController(ControllerHelper ch, CredentialService service) {
        this.ch = ch;
        this.service = service;
    }

    @GetMapping
    public List<BrainjRespDto> getAll() {
        service.getUserByToken();
        return ch.getAllBrainjs();
    }

    @GetMapping("/{id}")
    public BrainjRespDto getOne(@PathVariable Long id) {
        service.getUserByToken();
        return ch.getBrainjById(id);
    }
}
