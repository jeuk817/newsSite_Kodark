package com.kodark.news.dto;

import java.util.Date;

public class AuthStringDto {
	private int id;
	private String email;
	private String authString;
	private Date createdAt;
	private String confirm;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAuthString() {
		return authString;
	}
	public void setAuthString(String authString) {
		this.authString = authString;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	@Override
	public String toString() {
		return "AuthStringDto [id=" + id + ", email=" + email + ", authString=" + authString + ", createdAt="
				+ createdAt + ", confirm=" + confirm + "]";
	}
	
	
}
