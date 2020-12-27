package com.kodark.news.dto;

import java.util.Date;

import org.apache.ibatis.type.Alias;

/*
 * 작성자 hj
 * 작성일 12.23
 */
@Alias("CommentReportDto")
public class CommentReportDto {
	private int id;
	private String reason;
	private Date createdAt;
	private String doneFlag;
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
	public String getDoneFlag() {
		return doneFlag;
	}
	public void setDoneFlag(String doneFlag) {
		this.doneFlag = doneFlag;
	}
	
	@Override
	public String toString() {
		return "CommentReportDto [id=" + id + ", reason=" + reason + ", createdAt=" + createdAt + ", doneFlag="
				+ doneFlag + "]";
	}
	
}
