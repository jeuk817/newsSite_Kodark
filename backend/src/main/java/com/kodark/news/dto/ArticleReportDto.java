package com.kodark.news.dto;

import java.util.Date;

/*
 * 작성자 hj
 * 작성일 12.23
 */

public class ArticleReportDto {
	private int id;
	private String reason;
	private Date createdAt;
	private Date doneFlag;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getDoneFlag() {
		return doneFlag;
	}
	public void setDoneFlag(Date doneFlag) {
		this.doneFlag = doneFlag;
	}
	
	@Override
	public String toString() {
		return "ArticleReportDto [id=" + id + ", reason=" + reason + ", createdAt=" + createdAt + ", doneFlag="
				+ doneFlag + "]";
	}

}
