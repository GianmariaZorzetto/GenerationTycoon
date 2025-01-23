package com.generationtycoon.controllers;

import com.generationtycoon.controllers.exceptions.BrainjMissingException;
import com.generationtycoon.controllers.helpers.ControllerHelper;
import com.generationtycoon.model.dto.BrainjRespDto;
import com.generationtycoon.utils.credentials.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @ExceptionHandler(BrainjMissingException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleMissingBrainj(BrainjMissingException ex) {
        return ex.getMessage();
    }
}
