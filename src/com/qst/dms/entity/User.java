package com.qst.dms.entity;

public class User {
 
   public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
  
   public User(int id, String username, String password, int sex, String hobby, String address, String degree) {
	super();
	this.id = id;
	this.username = username;
	this.password = password;
	this.sex = sex;
	this.hobby = hobby;
	this.address = address;
	this.degree = degree;
   }
   public User() {
	// TODO 自动生成的构造函数存根
}
private int id;
   private String username;
   private String password;
   private int sex;
   private String hobby;
   private String address;
   private String  degree;
}
