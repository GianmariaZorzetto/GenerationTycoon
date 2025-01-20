package com.generationtycoon.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Kaboom extends BaseEntity
{
	@Column(nullable = false)
	private String question;
	@Column(nullable = false)
	private String answer1;
	@Column(nullable = false)
	private String answer2;
	@Column(nullable = false)
	private String answer3;
	@Column(nullable = false)
	private String answer4;
	@Column(nullable = false)
	private KaboomColors correctColor;

	public String getQuestion()
	{
		return question;
	}

	public void setQuestion(String question)
	{
		this.question = question;
	}

	public String getAnswer1()
	{
		return answer1;
	}

	public void setAnswer1(String answer1)
	{
		this.answer1 = answer1;
	}

	public String getAnswer2()
	{
		return answer2;
	}

	public void setAnswer2(String answer2)
	{
		this.answer2 = answer2;
	}

	public String getAnswer3()
	{
		return answer3;
	}

	public void setAnswer3(String answer3)
	{
		this.answer3 = answer3;
	}

	public String getAnswer4()
	{
		return answer4;
	}

	public void setAnswer4(String answer4)
	{
		this.answer4 = answer4;
	}

	public KaboomColors getCorrectColor()
	{
		return correctColor;
	}

	public void setCorrectColor(KaboomColors correctColor)
	{
		this.correctColor = correctColor;
	}

	public boolean isCorrectAnswer(KaboomColors color)
	{
		return correctColor.equals(color);
	}

}
