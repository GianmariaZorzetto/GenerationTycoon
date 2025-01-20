package com.generationtycoon.model.dto;

import com.generationtycoon.model.entities.User;
import org.springframework.stereotype.Service;

@Service
public class DtoConverter
{
	public User userLoginReqDtoToEntity(UserLoginReqDto dto)
	{
		User user = new User();
		user.setEmail(dto.email());
		user.setPassword(dto.password());
		return user;
	}

	public User userRegistrationReqDtoToEntity(UserRegistrationReqDto dto)
	{
		User user = new User();
		user.setEmail(dto.email());
		user.setUsername(dto.username());
		user.setPassword(dto.password());
		user.setDifficulty(dto.difficulty());
		return user;
	}

	public User userLeaderboardReqDtoToEntity(UserLeaderboardReqDto dto)
	{
		User user = new User();
		user.setUsername(dto.username());
		return user;
	}

	public UserLeaderboardRespDto toUserLeaderboardRespDto(User user)
	{
		return UserLeaderboardRespDto.of()
				.username(user.getUsername())
				.difficulty(user.getDifficulty())
				.score(user.getScore())
				.build();
	}

	public UserLoginRespDto toUserLoginRespDto(User user)
	{
		//TODO genera token
		return null;
	}


}
