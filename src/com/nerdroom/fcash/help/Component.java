package com.nerdroom.fcash.help;

import android.app.Activity;

public class Component {
	Activity mn;
	WidgetHelp wg;	
	
public Component(Activity _mn)
{
mn=_mn;
wg = new WidgetHelp(mn);
//sh= new setting_helper(mn);
}
}
