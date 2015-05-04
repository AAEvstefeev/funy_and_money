package com.nerdroom.funy;

import com.nerdroom.fcash.help.MyActivity;
import com.nerdroom.fcash.help.md5;
import com.nerdroom.fcash.model.Gender;
import com.nerdroom.fcash.model.RegData;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.*;
import com.nerdroom.funy.R;
import com.nerdroom.json.RegLouder;
import com.nerdroom.json.RemindPassLouder;
public class RemindPassActivity extends MyActivity{
	int DIALOG_DATE = 1;
	  int myYear = 0000;
	  int myMonth = 00;
	  int myDay = 00;
	
	
		Button btn_reg;
		EditText edt_mail;
	
		
		public boolean loud=false;
	//	RegLouder l;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.remind_pass);
			
			
			btn_reg=wg.get_b(R.id.btn_login);
			
			
			edt_mail=wg.get_te(R.id.edt_mail);
			
			
		
			set_btn();
		}
		public void set_btn()
		{
			btn_reg.setOnClickListener(new View.OnClickListener() 
		    {
		    	public void onClick(View v) 
		    	{
		    		if(!loud)
		    		{	
		    		String gender = null;	
		    		RegData rg=null;
		    	
		    		String mail=edt_mail.getText().toString();	
		    		
		       	boolean error=false;
		    	if(!mail.contains("@") || mail.length()<5) {error=true; wg.set_text(R.id.stat_tv,getString(R.string.wrong_mail));}
		    
		    		if(!error)new RemindPassLouder(mn).execute(mail);
		    		
		    		
		    		}
		     } });	
			
		
			Display display = getWindowManager().getDefaultDisplay();
			Point size = new Point();
			display.getSize(size);
			int width = size.x;
			int height = size.y;
			
		
			edt_mail.getLayoutParams().width=(width*3)/4;
			
			
			//edt_login.getLayoutParams().width=height*(1/10);
			//edt_pass.getLayoutParams().width=height*(1/10);
		}
	

		public static boolean isInteger(String s) {
		    try { 
		        Integer.parseInt(s); 
		    } catch(NumberFormatException e) { 
		        return false; 
		    }
		    // only got here if we didn't return false
		    return true;
		}
		 
		    

		    protected Dialog onCreateDialog(int id) {
		      if (id == DIALOG_DATE) {
		        DatePickerDialog tpd = new DatePickerDialog(this, myCallBack, myYear, myMonth, myDay);
		        return tpd;
		      }
		      return super.onCreateDialog(id);
		    }
		    
		    OnDateSetListener myCallBack = new OnDateSetListener() {

		    public void onDateSet(DatePicker view, int year, int monthOfYear,
		        int dayOfMonth) {
		      myYear = year;
		      myMonth = monthOfYear+1;
		      myDay = dayOfMonth;
		 
		    }
		    
		    };
}

