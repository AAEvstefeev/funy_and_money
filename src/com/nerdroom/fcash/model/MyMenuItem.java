package com.nerdroom.fcash.model;

public class MyMenuItem {
public String value;
public String title;
public MyMenuItem(String title,String value)
{
	this.title=title;
	this.value=value;
}
public MyMenuItem(categories c)
{

this.title=c.title;
this.value=c.id;



}
}
