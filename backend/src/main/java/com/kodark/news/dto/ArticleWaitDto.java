package com.kodark.news.dto;

public class ArticleWaitDto {
	String name;
	String email;
	int id;
	String title;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
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
	@Override
	public String toString() {
		return "ArticleWaitDto [name=" + name + ", email=" + email + ", id=" + id + ", title=" + title + "]";
	}
	
	
}
