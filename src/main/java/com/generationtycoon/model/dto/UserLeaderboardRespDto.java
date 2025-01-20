package com.generationtycoon.model.dto;

import com.generationtycoon.model.entities.Difficulty;
import com.generationtycoon.model.entities.User;

public record UserLeaderboardRespDto(String username, Difficulty difficulty, double score) {


	public static UserLeaderboardRespDtoBuilder of()
	{
		return new UserLeaderboardRespDtoBuilder();
	}

	public static class UserLeaderboardRespDtoBuilder
	{
		private String username;
		private Difficulty difficulty;
		private double score;

		private UserLeaderboardRespDtoBuilder(){}

		public UserLeaderboardRespDtoBuilder username(String username)
		{
			this.username = username;
			return this;
		}

		public UserLeaderboardRespDtoBuilder difficulty(Difficulty difficulty)
		{
			this.difficulty = difficulty;
			return this;
		}
		public UserLeaderboardRespDtoBuilder score(double score)
		{
			this.score = score;
			return this;
		}

		public UserLeaderboardRespDto build()
		{
			return new UserLeaderboardRespDto(username, difficulty, score);
		}


	}


}
