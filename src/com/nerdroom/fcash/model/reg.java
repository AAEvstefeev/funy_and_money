package com.nerdroom.fcash.model;

import com.google.gson.annotations.SerializedName;

public class reg {
	@SerializedName("email")
	public String email;
	@SerializedName("name")
	public String name;
	@SerializedName("age")
	public String age;
	@SerializedName("sex")
	public String sex;
	@SerializedName("password")
	public String password;
	@SerializedName("referer")
	public String referer;
	
public reg(String email, String name, String age, String sex, String password, String referer)
{
	this.email=email; 
	this.name=name;
	this.age=age;
	this.sex=sex;
	this.password=password; 
	this.referer=referer;
}
}
