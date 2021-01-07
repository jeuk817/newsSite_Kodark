package com.kodark.news.dto;


import java.util.Date;

import org.apache.ibatis.type.Alias;

/*
 * 작성자 hj
 * 작성일 12.23
 */

// Article / Category / Image / Article_Emotion
@Alias("ArticleDto")
public class ArticleDto {
	// Article
	private int articleId;
	private String title;
	private String content;
	private Date createdAt;
	private Date editedAt;
	private int hit;
	private String status;
	
	
	// Image
	private String image;
	private String sourceUrl;
	private String description;
	
	// Article_Emotion
	private String reputation;

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getEditedAt() {
		return editedAt;
	}

	public void setEditedAt(Date editedAt) {
		this.editedAt = editedAt;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReputation() {
		return reputation;
	}

	public void setReputation(String reputation) {
		this.reputation = reputation;
	}

	@Override
	public String toString() {
		return "ArticleDto [articleId=" + articleId + ", title=" + title + ", content=" + content + ", createdAt="
				+ createdAt + ", editedAt=" + editedAt + ", hit=" + hit + ", status=" + status + ", image=" + image
				+ ", sourceUrl=" + sourceUrl + ", description=" + description + ", reputation=" + reputation + "]";
	}

	
}
