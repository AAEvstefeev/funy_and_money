package com.nerdroom.funy;

import java.io.IOException;

import com.nerdroom.fcash.help.MyActivity;
import com.nerdroom.fcash.help.md5;
import com.nerdroom.fcash.model.RegData;
import com.nerdroom.json.Balance2Louder;
import com.nerdroom.json.LoginLouder;
import com.nerdroom.json.ReqMoneyLouder;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class GetMoneyActivity extends MyActivity{
	public static int balance=0;
	Button btn_send;
	EditText edt_value,edt_accaunt;
	TextView tv_name;
	RadioButton yandex,telefon,kiwi;
	Button btn_qiwi,btn_yandex,btn_telefon;
	
	//LoginLouder l;
	String name;
	boolean loud=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    Bundle extras = getIntent().getExtras();
		name= extras.getString("name"); 
		
		setContentView(R.layout.get_money);
		btn_send=wg.get_b(R.id.btn_send_req);
	
		edt_value=wg.get_te(R.id.edt_value);
		edt_accaunt=wg.get_te(R.id.edt_account);
	yandex=(RadioButton)findViewById(R.id.yandex);	
	kiwi=(RadioButton)findViewById(R.id.kiwi);
	telefon=(RadioButton)findViewById(R.id.telefon);
	btn_telefon=wg.get_b(R.id.btn_telefon);
	btn_qiwi=wg.get_b(R.id.btn_qiwi);
	btn_yandex=wg.get_b(R.id.btn_yandex);
		set_btn();
	tv_name=wg.get_tv(R.id.name);	
	tv_name.setText(name);	
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		int height = size.y;
		
		edt_value.getLayoutParams().width=(width*3)/4;
		edt_accaunt.getLayoutParams().width=(width*3)/4;
		new Balance2Louder(mn).execute(); 
		
	//	new ReqMoneyLouder(mn,"request_user").execute();
	}
	public void set_btn()
	{
		btn_qiwi.setOnClickListener(new View.OnClickListener() 
	    {
	    	public void onClick(View v) 
	    	{
	    	kiwi.setChecked(true);
	    	yandex.setChecked(false);
	    	telefon.setChecked(false);
	    	}
	    });
		btn_yandex.setOnClickListener(new View.OnClickListener() 
	    {
	    	public void onClick(View v) 
	    	{
	    		kiwi.setChecked(false);
		    	yandex.setChecked(true);
		    	telefon.setChecked(false);
	    	}
	    });
		btn_telefon.setOnClickListener(new View.OnClickListener() 
	    {
	    	public void onClick(View v) 
	    	{
	    		kiwi.setChecked(false);
		    	yandex.setChecked(false);
		    	telefon.setChecked(true);
	    	}
	    });
		btn_send.setOnClickListener(new View.OnClickListener() 
	    {
	    	public void onClick(View v) 
	    	{
	    	if(!loud)
	    		{
	    		boolean ok=true;
	    		String type=null;
	    		String value=edt_value.getText().toString();
	    		String  accaunt=edt_accaunt.getText().toString();
	    		
	    		if(yandex.isChecked()) type="yandex money";
	    		if(kiwi.isChecked()) type="kiwi";
	    		if(telefon.isChecked()) type="telefon";
	    		TextView tv_er=wg.get_tv(R.id.tv_error);
	    		
	    		
	    		if(value.equals(""))
	    		{
	    			ok=false;
	    			tv_er.setVisibility(View.VISIBLE);
	    			tv_er.setText(getString(R.string.er_suma));
	    		}
	    	
	    		
	    		if(accaunt.equals(""))
	    		{
	    			ok=false;	
	    			tv_er.setVisibility(View.VISIBLE);
	    			tv_er.setText(getString(R.string.er_acc));
	    		}
	    		
	    		if(!TextUtils.isDigitsOnly(value))
	    		{
	    			ok=false;
	    			tv_er.setVisibility(View.VISIBLE);
	    			tv_er.setText(getString(R.string.er_suma));		
	    		}
	    		else
	    		{
	    			try {
	    				if(balance<Integer.valueOf(value))
		    			{
			    			ok=false;
			    			tv_er.setVisibility(View.VISIBLE);
			    			tv_er.setText(getString(R.string.er_balance));		
			    		}	
	    				} catch (NumberFormatException e) {
	    					ok=false; 
	    				}
	    			
	    		}
	    		if(type==null)
	    		{
	    			ok=false;
	    			tv_er.setVisibility(View.VISIBLE);
	    			tv_er.setText(getString(R.string.er_type));		
	    		}
	    		
	    if(ok)
	    		new ReqMoneyLouder(mn,"request_create",accaunt,value,type).execute();
	    	
	    		}
	     } });	
		
	}
}
