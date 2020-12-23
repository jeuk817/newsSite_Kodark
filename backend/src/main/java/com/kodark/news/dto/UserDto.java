package com.kodark.news.dto;
/**
 * user / user_detail
 * @author ys
 * 2020-12-23
 */

import java.util.Date;

public class UserDto {
	//user
	private int id;
	private String email;
	private String pwd;
	private String auth;
	
	//user_detail
	private String nickName;
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
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
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
		return "UserDto [id=" + id + ", email=" + email + ", pwd=" + pwd + ", auth=" + auth + ", nickName=" + nickName
				+ ", name=" + name + ", local=" + local + ", birth=" + birth + ", gender=" + gender + ", image=" + image
				+ "]";
	}
	
}
