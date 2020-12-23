package com.kodark.news.dto;
/**
 * Subscribe
 * @author ys
 * 2020-12-23
 */
public class SubscribeDto {
	private int id;
	private String letterAccepted;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLetterAccepted() {
		return letterAccepted;
	}
	public void setLetterAccepted(String letterAccepted) {
		this.letterAccepted = letterAccepted;
	}
	@Override
	public String toString() {
		return "SubscribeDto [id=" + id + ", letterAccepted=" + letterAccepted + "]";
	}
	
	
}
