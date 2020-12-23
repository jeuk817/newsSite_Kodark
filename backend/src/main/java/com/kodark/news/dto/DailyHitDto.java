package com.kodark.news.dto;

import java.util.Date;

/*
 * 작성자 hj
 * 작성일 12.23
 */

public class DailyHitDto {
	private int id;
	private Date day;
	private int hit;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDay() {
		return day;
	}
	public void setDay(Date day) {
		this.day = day;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	
	@Override
	public String toString() {
		return "DailyHitDto [id=" + id + ", day=" + day + ", hit=" + hit + "]";
	}
	
}
