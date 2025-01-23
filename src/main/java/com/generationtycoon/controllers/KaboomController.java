package com.generationtycoon.controllers;

import com.generationtycoon.controllers.helpers.ControllerHelper;
import com.generationtycoon.model.dto.KaboomRespDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/kaboom")
public class KaboomController
{
	@Autowired
	ControllerHelper ch;

	@GetMapping
	public List<KaboomRespDto> getAll()
	{
		return ch.getAllKabooms();
	}

	@GetMapping("/{id}")
	public KaboomRespDto getOne(@PathVariable long id)
	{
		return ch.getKaboomById(id);
	}
}
