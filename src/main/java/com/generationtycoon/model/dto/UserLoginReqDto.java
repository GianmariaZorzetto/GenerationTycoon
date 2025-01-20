package com.generationtycoon.model.dto;

public record UserLoginReqDto(String email, String password)
{

	public static UserLoginReqDtoBuilder of()
	{
		return new UserLoginReqDtoBuilder();
	}

	public static class UserLoginReqDtoBuilder
	{
		private String email;
		private String password;

		private UserLoginReqDtoBuilder()
		{
		}

		public UserLoginReqDtoBuilder email(String email)
		{
			this.email = email;
			return this;
		}

		public UserLoginReqDtoBuilder password(String password)
		{
			this.password = password;
			return this;
		}

		public UserLoginReqDto build()
		{
			return new UserLoginReqDto(email, password);
		}

	}

}
