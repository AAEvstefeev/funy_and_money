package com.nerdroom.fcash.model;

public class RegData {
public String name;
public String login;
public String password;
public String email;
public String age;
public String sex;
public String  referer; 

public RegData(String _login,String _pass, String _mail)
{
email=_mail;
login=_login;
password=_pass;

}
public RegData(String _pass, String _mail)
{
email=_mail;

password=_pass;

}
public RegData(String _login,String _pass, String _mail,String _name,String _age,String _gender,String _referer)
{
email=_mail;
name=_login;
password=_pass;
name=_name;
age=_age;//Integer.parseInt(_age);
sex=_gender;
referer=_referer;
}
public RegData() {
	// TODO Auto-generated constructor stub
}
}