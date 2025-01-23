package com.generationtycoon.controllers;

import com.generationtycoon.controllers.helpers.ControllerHelper;
import com.generationtycoon.model.dto.UserLeaderboardRespDto;
import com.generationtycoon.model.entities.Difficulty;
import com.generationtycoon.utils.credentials.CredentialService;
import com.generationtycoon.utils.score.ScoreLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController
{
	@Autowired
	ControllerHelper ch;

	@Autowired
	CredentialService cs;

	@Autowired
	ScoreLogic sl;

	@GetMapping
	public List<UserLeaderboardRespDto> getAll()
	{
		return ch.getAllUsersOnTheLeaderboard();
	}

	@GetMapping("/{difficulty}")
	public List<UserLeaderboardRespDto> getAllByDifficulty(@PathVariable Difficulty difficulty)
	{
		return ch.getUsersByDifficulty(difficulty);
	}

	@DeleteMapping("/{id}")
	public Long delete(@PathVariable long id)
	{
		ch.deleteUser(id);
		return id;
	}
}
