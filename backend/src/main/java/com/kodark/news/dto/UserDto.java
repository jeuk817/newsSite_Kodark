package com.kodark.news.dto;


import java.util.Date;

import org.apache.ibatis.type.Alias;

/*
 * 작성자 hj
 * 작성일 12.23
 */

// User / UserDetail
@Alias("UserDto")
public class UserDto {
	// User
	private int id;
	private String email;
	private String pwd;
	private String auth;
	
	// UserDetail
	private String nickname;
	private String name;
	private String local;
	private Date birth;
	private String gender;
	private String image;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
	@Override
	public String toString() {
		return "UserDto [id=" + id + ", email=" + email + ", pwd=" + pwd + ", auth=" + auth + ", nickname=" + nickname
				+ ", name=" + name + ", local=" + local + ", birth=" + birth + ", gender=" + gender + ", image=" + image
				+ "]";
	}

}
