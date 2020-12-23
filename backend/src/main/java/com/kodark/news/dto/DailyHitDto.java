package com.kodark.news.dto;
/**
 * DailyHit
 * @author ys
 * 2020-12-23
 */

import java.util.Date;

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
