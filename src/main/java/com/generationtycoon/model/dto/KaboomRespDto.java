package com.generationtycoon.model.dto;

import com.generationtycoon.model.entities.KaboomColors;

public record KaboomRespDto(String question, String answer1, String answer2, String answer3, String answer4, KaboomColors correctColor)
{

	public static KaboomRespDtoBuilder builder()
	{
		return new KaboomRespDtoBuilder();
	}

	public static class KaboomRespDtoBuilder
	{
		private String question;
		private String answer1;
		private String answer2;
		private String answer3;
		private String answer4;
		private KaboomColors correctColor;

		private KaboomRespDtoBuilder(){}

		public KaboomRespDtoBuilder question(String question)
		{
			this.question = question;
			return this;
		}

		public KaboomRespDtoBuilder answer1(String answer1)
		{
			this.answer1 = answer1;
			return this;
		}
		public KaboomRespDtoBuilder answer2(String answer2)
		{
			this.answer2 = answer2;
			return this;
		}
		public KaboomRespDtoBuilder answer3(String answer3)
		{
			this.answer3 = answer3;
			return this;
		}
		public KaboomRespDtoBuilder answer4(String answer4)
		{
			this.answer4 = answer4;
			return this;
		}

		public KaboomRespDtoBuilder correctColor(KaboomColors correctColor)
		{
			this.correctColor = correctColor;
			return this;
		}

		public KaboomRespDto build()
		{
			return new KaboomRespDto(question, answer1, answer2, answer3, answer4, correctColor);
		}

	}

}
