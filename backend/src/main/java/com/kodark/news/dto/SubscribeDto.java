package com.kodark.news.dto;


/*
 * 작성자 hj
 * 작성일 12.23
 */

public class SubscribeDto {
	private String letterAccepted;

	public String getLetterAccepted() {
		return letterAccepted;
	}

	public void setLetterAccepted(String letterAccepted) {
		this.letterAccepted = letterAccepted;
	}

	@Override
	public String toString() {
		return "SubscribeDto [letterAccepted=" + letterAccepted + "]";
	}
	

}
