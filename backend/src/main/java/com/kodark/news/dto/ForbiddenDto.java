package com.kodark.news.dto;


import java.util.Date;

/*
 * 작성자 hj
 * 작성일 12.23
 */

public class ForbiddenDto {
	private String status;
	private String reason;
	private Date endDate;
	private Date createdAt;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	@Override
	public String toString() {
		return "ForbiddenDto [status=" + status + ", reason=" + reason + ", endDate=" + endDate + ", createdAt="
				+ createdAt + "]";
	}

}
