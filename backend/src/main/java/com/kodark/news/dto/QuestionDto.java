package com.kodark.news.dto;

/*
 * 작성자 hj
 * 작성일 12.23
 */

// Question / Answer
public class QuestionDto {
	//Question
	private int id;
	private String title;
	private String questionContent;
	
	//Answer
	private String answerContent;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getQuestionContent() {
		return questionContent;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	public String getAnswerContent() {
		return answerContent;
	}

	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}

	@Override
	public String toString() {
		return "QuestionDto [id=" + id + ", title=" + title + ", questionContent=" + questionContent
				+ ", answerContent=" + answerContent + "]";
	}
	
	
}
