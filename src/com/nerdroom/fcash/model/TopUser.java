package com.nerdroom.fcash.model;

public class TopUser {
public String name;
public int coin;
public String id;

public  TopUser(String name,int coin)
{
this.name=name;
this.coin=coin;

}
public  TopUser(String id,String name,int coin)
{
this.name=name;
this.coin=coin;
this.id=id;
}
}
