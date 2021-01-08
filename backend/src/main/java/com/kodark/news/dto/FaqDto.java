package com.kodark.news.dto;

import org.apache.ibatis.type.Alias;

/*
 * 작성자 hj
 * 작성일 12.23
 */
@Alias("FaqDto")
public class FaqDto {
	private int id;
	private String title;
	private String content;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "FaqDto [id=" + id + ", title=" + title + ", content=" + content + "]";
	}


}
