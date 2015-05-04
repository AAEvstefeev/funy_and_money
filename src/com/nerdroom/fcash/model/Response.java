package com.nerdroom.fcash.model;

import com.google.gson.annotations.SerializedName;

public class Response {
@SerializedName("status")
public int status;
@SerializedName("count")
public int count;
@SerializedName("categories")
public categories[] categories;
@SerializedName("top_list")
public TopList[] top_list;
@SerializedName("text")
public String text;
@SerializedName("info")
public String info;
@SerializedName("version")
public int version;
@SerializedName("token")
public String token;
@SerializedName("login_time")
public int login_time;
@SerializedName("auth")
public int auth;
@SerializedName("id_from")
public int id_from;
@SerializedName("id_to")
public int id_to;

@SerializedName("user_id")
public String user_id;
@SerializedName("admin")
public String admin;
@SerializedName("referer_code")
public String referer_code;
@SerializedName("name")
public String name;
@SerializedName("value")
public long value;
@SerializedName("operations")
public int operations;
@SerializedName("device_id")
public int device_id;
@SerializedName("referers")
public String[] referers;
@SerializedName("key")
public String key;
@SerializedName("type_id")
public int type_id;
@SerializedName("account")
public int account;
@SerializedName("type")
public String type;
@SerializedName("client_id")
public String client_id;
@SerializedName("request_list")
public request_list[] request_list;
@SerializedName("amount")
public int amount;



//{"status":1,"login_time":1383290208,"token":"4b6030968376a216d5d98ca49206acf9","text":"Login Successfull","info":"QRME Service Development","version":1}
public Response(int _status, String _text,String _info,int _version,String _token,int _login_time)
{
status=_status;
text=_text;
info=_info;
version=_version;
token=_token;
login_time=_login_time;
}
public Response(int _status, String _text,String _info,int _version)
{
	status=_status;
	text=_text;
	info=_info;
	version=_version;
}
public Response() {
	// TODO Auto-generated constructor stub
}
}
