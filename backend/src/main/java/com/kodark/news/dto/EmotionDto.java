package com.kodark.news.dto;


/*
 * 작성자 hj
 * 작성일 12.23
 */

public class EmotionDto {
	private int id;
	private String status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "EmotionDto [id=" + id + ", status=" + status + "]";
	}

}
