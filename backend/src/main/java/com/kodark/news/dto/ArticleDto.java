package com.kodark.news.dto;

import java.util.Date;
/**
 * Article / Article_Emotion / Category / Image
 * 
 * @author ys
 *
 */
public class ArticleDto {
	//article
	private int articleId;  
	private String title;
	private String content;
	private Date createdAt;
	private Date editedAt;
	private int hit;
	private String status;
	//category
	private int categoryId;
	private String categoryName;
	//article_emotion
	private String repuatation;
	//image
	private String image;
	private String sourceUrl;
	private String descrption;
	
	
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
	public Date getCreated_at() {
		return createdAt;
	}
	public void setCreated_at(Date created_at) {
		this.createdAt = created_at;
	}
	public Date getEdited_at() {
		return editedAt;
	}
	public void setEdited_at(Date edited_at) {
		this.editedAt = edited_at;
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
	public String getRepuatation() {
		return repuatation;
	}
	public void setRepuatation(String repuatation) {
		this.repuatation = repuatation;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getSource_Url() {
		return sourceUrl;
	}
	public void setSource_Url(String source_Url) {
		this.sourceUrl = source_Url;
	}
	public String getDescrption() {
		return descrption;
	}
	public void setDescrption(String descrption) {
		this.descrption = descrption;
	}
	@Override
	public String toString() {
		return "ArticleDto [articleId=" + articleId + ", categoryId=" + categoryId + ", categoryName=" + categoryName
				+ ", title=" + title + ", content=" + content + ", createdAt=" + createdAt + ", editedAt="
				+ editedAt + ", hit=" + hit + ", status=" + status + ", repuatation=" + repuatation + ", image="
				+ image + ", sourceUrl=" + sourceUrl + ", descrption=" + descrption + "]";
	}
	
	
	
}
