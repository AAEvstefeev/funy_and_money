package com.nerdroom.funy;

import java.io.IOException;

import com.nerdroom.fcash.help.MyActivity;
import com.nerdroom.fcash.help.md5;
import com.nerdroom.fcash.model.RegData;
import com.nerdroom.json.LoginLouder;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends MyActivity{
	
	Button btn_login,btn_r_pass;
	EditText edt_pass,edt_login;
	//LoginLouder l;
	boolean loud=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		
		setContentView(R.layout.login);
		btn_login=wg.get_b(R.id.btn_login);
		btn_r_pass=wg.get_b(R.id.btn_passremind);
		edt_login=wg.get_te(R.id.edt_login);
		edt_pass=wg.get_te(R.id.edt_pass);
		
	
		set_btn();
		
		
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		int height = size.y;
		
		edt_login.getLayoutParams().width=(width*3)/4;
		edt_pass.getLayoutParams().width=(width*3)/4;
		//edt_login.getLayoutParams().width=height*(1/10);
		//edt_pass.getLayoutParams().width=height*(1/10);
	}
	public void set_btn()
	{
		btn_login.setOnClickListener(new View.OnClickListener() 
	    {
	    	public void onClick(View v) 
	    	{
	    	if(!loud)
	    		{
	    		String pass=edt_pass.getText().toString();
	    		RegData rg=new RegData(pass,edt_login.getText().toString());
	    		LoginLouder l = new LoginLouder(mn);
	    		l.execute(rg);
	    		}
	     } });	
		btn_r_pass.setOnClickListener(new View.OnClickListener() 
	    {
	    	public void onClick(View v) 
	    	{
	    	
	    		Intent i=new Intent(mn,com.nerdroom.funy.RemindPassActivity.class);
	    		
	    		startActivity(i);
	     } });	
	}
}
