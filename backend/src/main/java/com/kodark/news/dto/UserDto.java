package com.kodark.news.dto;



import org.apache.ibatis.type.Alias;

/*
 * �옉�꽦�옄 hj
 * �옉�꽦�씪 12.23
 */

// User / UserDetail
@Alias("UserDto")
public class UserDto {
	// User
	private int id;
	private String email;
	private String pwd;
	private String auth;
	
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
	@Override
	public String toString() {
		return "UserDto [id=" + id + ", email=" + email + ", pwd=" + pwd + ", auth=" + auth + "]";
	}
	



}
