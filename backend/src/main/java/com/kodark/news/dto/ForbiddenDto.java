package com.kodark.news.dto;

import java.util.Date;
/**
 * Forbidden
 * @author ys
 * 2020-12-23
 */
public class ForbiddenDto {
	private int userId;
	private String status;
	private String reason;
	private Date endDate;
	private Date createdAt;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
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
		return "ForbiddenDto [userId=" + userId + ", status=" + status + ", reason=" + reason + ", endDate=" + endDate
				+ ", createdAt=" + createdAt + "]";
	}
	
	
}
