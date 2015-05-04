package com.nerdroom.fcash.help;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.webkit.WebView;
import android.widget.*;

public class WidgetHelp {
	Activity ctx;
public WidgetHelp(Activity c)
{
	ctx=c;
}
public String get_text(int id)
{
	EditText et = (EditText)ctx.findViewById(id);
	
	return et.getText().toString();
}
public String get_text(EditText et)
{
	return et.getText().toString();
}
public EditText get_te(int id)
{
	EditText et = (EditText)ctx.findViewById(id);
	return et;
}
public Button get_b(int id)
{
	Button b = (Button)ctx.findViewById(id);
	return b;
}
public CheckBox get_ch(int id)
{
	CheckBox ch = (CheckBox)ctx.findViewById(id);
	return ch;
}
public TextView get_tv(int id)
{
	TextView b = (TextView)ctx.findViewById(id);
	return b;
}
public ProgressBar get_bar(int id)
{
	ProgressBar b = (ProgressBar)ctx.findViewById(id);
	return b;
}
public RadioButton get_rb(int id)
{
	RadioButton b = (RadioButton)ctx.findViewById(id);
	return b;
}
public ImageView get_iv(int id)
{
	ImageView b = (ImageView)ctx.findViewById(id);
	return b;
}
public WebView get_wv(int id)
{
	WebView b = (WebView)ctx.findViewById(id);
	return b;
}
public void set_visible(int id)
{
	View b = (View)ctx.findViewById(id);
	b.setVisibility(View.VISIBLE);
	
}
public void set_gone(int id)
{
	View b = (View)ctx.findViewById(id);
	b.setVisibility(View.GONE);
	
}
public void set_text(int id,String st)
{
	TextView b = (TextView)ctx.findViewById(id);
	b.setText(st);
	
}
}
