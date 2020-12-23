package com.kodark.news.dto;
/**
 * Comm_Report
 * @author ys
 * 2020-12-23
 */

import java.util.Date;

public class CommReport {	
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
		return "CommReport [id=" + id + ", reason=" + reason + ", createdAt=" + createdAt + ", doneFlag=" + doneFlag
				+ "]";
	}
	
	

	
	
}
