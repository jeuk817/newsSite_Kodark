package com.kodark.news.dto;


import java.util.Date;

import org.apache.ibatis.type.Alias;

/*
 * 작성자 hj
 * 작성일 12.23
 */

// Comment / Comm_reputation
@Alias("CommentDto")
public class CommentDto {
	// Comment
	private int id;
	private int commentId;
	private String content;
	private Date createdAt;
	private String delFlag;
	
	// Comm_repuatation
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
	@Override
	public String toString() {
		return "CommentDto [id=" + id + ", commentId=" + commentId + ", content=" + content + ", createdAt=" + createdAt
				+ ", delFlag=" + delFlag + "]";
	}

}
