package com.generationtycoon.controllers;

import com.generationtycoon.controllers.helpers.ControllerHelper;
import com.generationtycoon.model.dto.BrainjRespDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/brainj")
public class BrainjController
{
	@Autowired
	ControllerHelper ch;

	@GetMapping
	public List<BrainjRespDto> getAll()
	{
		return ch.getAllBrainjs();
	}

	@GetMapping("/{id}")
	public BrainjRespDto getOne(@PathVariable long id)
	{
		return ch.getBrainjById(id);
	}
}
