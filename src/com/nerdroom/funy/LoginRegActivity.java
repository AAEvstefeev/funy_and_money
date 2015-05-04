package com.nerdroom.funy;

import com.nerdroom.fcash.help.MyActivity;
import com.nerdroom.fcash.model.RegData;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class LoginRegActivity extends MyActivity {
	LoginRegActivity mn;
//	widget_help wg;
//	setting_helper sh;
	Button btn_reg,btn_login;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);
		mn=this;

		btn_reg=wg.get_b(R.id.btn_reg);
		btn_login=wg.get_b(R.id.btn_login);
		RegData rg= sh.get_reg_data();
	if(rg!=null)
		if(!rg.login.equals(""))
			{
			Intent intent = new Intent(mn,WorkActivity.class);
	    	mn.startActivity(intent);
			}
		else
			{
			set_btn();
			}
	if(rg==null)	set_btn();
	}
	
	
	public void set_btn()
	{
		btn_reg.setOnClickListener(new View.OnClickListener() 
	    {
	    	public void onClick(View v) 
	    	{
	    	Intent reg_intent = new Intent(mn,RegActivity.class);
	    	mn.startActivity(reg_intent);
	     } });	
		btn_login.setOnClickListener(new View.OnClickListener() 
	    {
	    	public void onClick(View v) 
	    	{
	    	Intent  login_intent = new Intent(mn,LoginActivity.class);
	    	mn.startActivity(login_intent);
	     } });	
	}

	
}
