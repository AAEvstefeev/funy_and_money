package com.nerdroom.funy;

import java.io.IOException;

import com.nerdroom.fcash.help.MyActivity;
import com.nerdroom.fcash.help.md5;
import com.nerdroom.fcash.model.RegData;
import com.nerdroom.json.BalanceLouder;
import com.nerdroom.json.LoginLouder;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class BalanceActivity extends MyActivity{
	
	Button btn_send;
	EditText edt_value,edt_accaunt;
	RadioButton yandex,telefon,kiwi;
	//LoginLouder l;
	boolean loud=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		
		setContentView(R.layout.balance);
		new BalanceLouder(mn).execute();
	//	btn_send=wg.get_b(R.id.btn_send_req);
	
//		edt_value=wg.get_te(R.id.edt_value);
	//	edt_accaunt=wg.get_te(R.id.edt_account);
		
	
		//set_btn();
		
		
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		int height = size.y;
		
	//	edt_value.getLayoutParams().width=(width*3)/4;
		//edt_accaunt.getLayoutParams().width=(width*3)/4;
		//edt_login.getLayoutParams().width=height*(1/10);
		//edt_pass.getLayoutParams().width=height*(1/10);
	}
	public void set_btn()
	{
		btn_send.setOnClickListener(new View.OnClickListener() 
	    {
	    	public void onClick(View v) 
	    	{
	    	if(!loud)
	    		{
	    		String value=edt_value.getText().toString();
	    		String  accaunt=edt_accaunt.getText().toString();
	    	//	RegData rg=new RegData(pass,edt_login.getText().toString());
	    //		LoginLouder l = new LoginLouder(mn);
	    	//	l.execute(rg);
	    		}
	     } });	
		
	}
}
