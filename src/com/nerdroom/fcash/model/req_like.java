package com.nerdroom.fcash.model;

public class req_like {
String device_id;
String article_id;

public req_like(String device_id, String article_id)
{
	this.device_id=device_id;
	this.article_id=article_id;
}
public req_like( String article_id)
{
	this.device_id=null;
	this.article_id=article_id;
}
}
