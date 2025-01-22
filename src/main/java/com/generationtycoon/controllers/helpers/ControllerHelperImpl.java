package com.generationtycoon.controllers.helpers;

import com.generationtycoon.controllers.exceptions.BrainjMissingException;
import com.generationtycoon.controllers.exceptions.KaboomMissingException;
import com.generationtycoon.controllers.exceptions.UserMissingException;
import com.generationtycoon.model.dto.*;
import com.generationtycoon.model.entities.Brainj;
import com.generationtycoon.model.entities.Difficulty;
import com.generationtycoon.model.entities.Kaboom;
import com.generationtycoon.model.entities.User;
import com.generationtycoon.model.repositories.BrainjRepository;
import com.generationtycoon.model.repositories.KaboomRepository;
import com.generationtycoon.model.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ControllerHelperImpl implements ControllerHelper
{

	BrainjRepository bRepo;

	KaboomRepository kRepo;

	UserRepository uRepo;

	DtoConverter converter;

	@Override
	public List<KaboomRespDto> getAllKabooms()
	{
		return kRepo.findAll().stream().map(kaboom -> converter.toKaboomRespDto(kaboom)).toList();
	}

	@Override
	public KaboomRespDto getKaboomById(Long id)
	{
		Optional<Kaboom> kaboom = kRepo.findById(id);

		if(kaboom.isEmpty())
			throw new KaboomMissingException("Kaboom non presente");

		return converter.toKaboomRespDto(kaboom.get());
	}

	@Override
	public List<UserLeaderboardRespDto> getAllUsersOnTheLeaderboard()
	{
		return uRepo.findAll().stream().map(user -> converter.toUserLeaderboardRespDto(user)).toList();
	}

	@Override
	public UserLoginRespDto getUserById(Long id)
	{
		Optional<User> user = uRepo.findById(id);

		if(user.isEmpty())
			throw new UserMissingException("User non presente");

		return converter.toUserLoginRespDto(user.get());
	}

	@Override
	public List<UserLeaderboardRespDto> getUsersByDifficulty(Difficulty difficulty)
	{
		return uRepo.findByDifficulty(difficulty).stream().map(user -> converter.toUserLeaderboardRespDto(user)).toList();
	}


	@Override
	public void deleteUser(Long id)
	{
		Optional<User> user = uRepo.findById(id);

		if(user.isEmpty())
			throw new UserMissingException("User non presente");

		uRepo.deleteById(id);
	}

	@Override
	public List<BrainjRespDto> getAllBrainjs()
	{
		return bRepo.findAll().stream().map(brainj -> converter.toBrainjRespDto(brainj)).toList();
	}

	@Override
	public BrainjRespDto getBrainjById(Long id)
	{
		Optional<Brainj> brainj = bRepo.findById(id);

		if(brainj.isEmpty())
			throw new BrainjMissingException("Brainj non presente");

		return converter.toBrainjRespDto(brainj.get());
	}
}
