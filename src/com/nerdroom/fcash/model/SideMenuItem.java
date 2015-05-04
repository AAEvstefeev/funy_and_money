package com.nerdroom.fcash.model;

import android.graphics.Bitmap;


public class SideMenuItem {

final public static int LOGIN=0;
final public static int REG=1;
final public static int INVITE=2;
final public static int TOP=3;
final public static int HELP=4;
final public static int LK=5;
final public static int LOGOUT=6;
final public static int EXIT=7;

public String title;
public  int  open;
public Bitmap pic;


public SideMenuItem(String title, int open, Bitmap pic)
{
this.title=title;;
this.open=open;
this.pic=pic;
}
}
