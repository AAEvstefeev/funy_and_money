package com.nerdroom.fcash.model;

public class ReqMoney {
public String user_id;
public String client_id;
public String token;
public String account;
public String type;
public String req_id;
public int value;
public ReqMoney(String token,String user_id,String accaunt,String type,int money)
{
this.token=token;
this.user_id=user_id;
this.client_id=user_id;
this.account=accaunt;
this.type=type;
this.value=money;
}
public ReqMoney(String token,String user_id,String req_id)
{
this.token=token;
this.user_id=user_id;
this.client_id=user_id;
this.req_id=req_id;
}
public ReqMoney(String token,String user_id)
{
this.token=token;
this.user_id=user_id;
this.client_id=user_id;

}
}
