package com.nerdroom.funy;

import java.io.IOException;

import com.nerdroom.fcash.help.Account;
import com.nerdroom.fcash.help.MyActivity;
import com.nerdroom.fcash.help.md5;
import com.nerdroom.fcash.model.ChangePass;
import com.nerdroom.fcash.model.RegData;
import com.nerdroom.json.ChangePassLouder;
import com.nerdroom.json.LoginLouder;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ChangePassActivity extends MyActivity{
	
	Button btn_change;
	EditText edt_old_pass,edt_new_pass2,edt_new_pass;
	TextView tv_stat;
	//LoginLouder l;
	boolean loud=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		
		setContentView(R.layout.change_pass);
		btn_change=wg.get_b(R.id.change_pass);
		tv_stat=wg.get_tv(R.id.stat_tv);
	
		edt_old_pass=wg.get_te(R.id.edt_old_pass);
		edt_new_pass2=wg.get_te(R.id.edt_new_pass2);
		edt_new_pass=wg.get_te(R.id.edt_new_pass);
		
		
	
		set_btn();
		
		
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		int height = size.y;
		
		edt_old_pass.getLayoutParams().width=(width*3)/4;
		edt_new_pass.getLayoutParams().width=(width*3)/4;
		edt_new_pass2.getLayoutParams().width=(width*3)/4;

	}
	public void set_btn()
	{
		btn_change.setOnClickListener(new View.OnClickListener() 
	    {
	    	public void onClick(View v) 
	    	{
	    	if(!loud)
	    		{
	    		String old_pass=edt_old_pass.getText().toString();
	    		String pass=edt_new_pass.getText().toString();
	    		String pass2=edt_new_pass2.getText().toString();
	    		boolean error=false;
	    		if(!pass.equals(pass)){error=true;tv_stat.setText(getText(R.string.pass_not_equal));}
	    		if(old_pass.equals("")){error=true;tv_stat.setText(getText(R.string.not_write));}
	    		if(pass2.equals("")){error=true;tv_stat.setText(getText(R.string.not_write));}
	    		if(pass.equals("")){error=true;tv_stat.setText(getText(R.string.not_write));}
	    	
	    	if(!error)
	    	{
	    		Account ac= new Account();
	    		ac.restore(mn);
	    		
	    		ChangePass chs = new ChangePass();
	    		chs.new_password=pass;
	    		chs.old_password=md5.md5(old_pass);
	    		chs.token=ac.token;
	    		chs.user_id=ac.user_id;	    	
	    		 new ChangePassLouder(mn).execute(chs);
	    	}
	    	else 
	    		tv_stat.setVisibility(View.VISIBLE);
	    		}
	     } });	

	}
}
