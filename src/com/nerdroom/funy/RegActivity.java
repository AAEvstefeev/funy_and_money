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
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.*;
import com.nerdroom.funy.R;
import com.nerdroom.json.RegLouder;
public class RegActivity extends MyActivity implements OnKeyListener{
	int DIALOG_DATE = 1;
	  int myYear = 1996;
	  int myMonth = 00;
	  int myDay = 00;
	
	
		Button btn_reg,btn_age;
		EditText edt_pass,edt_pass2,edt_login,edt_mail,edt_name,edt_age,edt_ref;
		TextView tv_age,t_age;
		RadioButton r_man,r_woman;
		public boolean loud=false;
	//	RegLouder l;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.reg);
			
			
			btn_reg=wg.get_b(R.id.btn_reg);
			btn_age=wg.get_b(R.id.btn_age1);
			edt_ref=wg.get_te(R.id.edt_ref);
			edt_login=wg.get_te(R.id.edt_login);
			edt_pass=wg.get_te(R.id.edt_pass);
			edt_pass2=wg.get_te(R.id.edt_pass2);
			edt_mail=wg.get_te(R.id.edt_mail);
			tv_age=wg.get_tv(R.id.txt_age);
			t_age=wg.get_tv(R.id.t_age);
			
			edt_name=wg.get_te(R.id.edt_name);
			r_man=wg.get_rb(R.id.r_man);
			r_woman=wg.get_rb(R.id.r_woman);
			
			
			edt_ref.setOnKeyListener(this);
			edt_login.setOnKeyListener(this);
			edt_pass.setOnKeyListener(this);
			edt_pass2.setOnKeyListener(this);
			edt_mail.setOnKeyListener(this);
			edt_name.setOnKeyListener(this);
			set_btn();
		}
		public void set_btn()
		{
			btn_reg.setOnClickListener(new View.OnClickListener() 
		    {
		    	public void onClick(View v) 
		    	{
		    		reg();
		     }

			 });	
			
			btn_age.setOnClickListener(new View.OnClickListener() 
		    {
		    	public void onClick(View v) 
		    	{
		    		showDialog(DIALOG_DATE);	
		     } });	
			tv_age.setOnClickListener(new View.OnClickListener() 
		    {
		    	public void onClick(View v) 
		    	{
		    		showDialog(DIALOG_DATE);	
		     } });	
			Display display = getWindowManager().getDefaultDisplay();
			Point size = new Point();
			display.getSize(size);
			int width = size.x;
			int height = size.y;
			
			edt_login.getLayoutParams().width=(width*3)/4;
			edt_pass.getLayoutParams().width=(width*3)/4;
			edt_pass2.getLayoutParams().width=(width*3)/4;
			edt_name.getLayoutParams().width=(width*3)/4;
			edt_mail.getLayoutParams().width=(width*3)/4;
			tv_age.getLayoutParams().width=(width*3)/4;
			edt_ref.getLayoutParams().width=(width*3)/4;
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
		      tv_age.setText( myDay + "/" + myMonth + "/" + myYear);
		      tv_age.setVisibility(View.VISIBLE);
		      t_age.setVisibility(View.VISIBLE);
		      btn_age.setVisibility(View.GONE);
		    }
		    
		    };
		    
		    public boolean onKey(View v, int keyCode, KeyEvent event) {
		        switch (keyCode) {
		            case KeyEvent.KEYCODE_ENTER:
		             /* This is a sample for handling the Enter button */
		            	reg();
		          return true;
		        }
		        return false;
		    }
		    
		    
			private void reg() {
				// TODO Auto-generated method stub
				if(!loud)
	    		{	
	    		String gender = null;	
	    		RegData rg=null;
	    		if(r_man.isChecked())
	    			gender="M"; 
	    		else 
	    			gender="W";
	    		
	    		
	    		String login=edt_login.getText().toString();
	    		String pass=edt_pass.getText().toString();	
	    		String pass2=edt_pass2.getText().toString();	
	    		String ref=edt_ref.getText().toString();
	    		String mail=edt_mail.getText().toString();	
	    		String name=edt_name.getText().toString();	
	    		String age=tv_age.getText().toString();	
	    		
	    	boolean error=false;
	    	if(!mail.contains("@") || mail.length()<5) {error=true; wg.set_text(R.id.stat_tv,getString(R.string.wrong_mail));}
	    	if(name.length()<1) {error=true; wg.set_text(R.id.stat_tv,getString(R.string.no_name));}	
	    	if(pass.length()<1) {error=true; wg.set_text(R.id.stat_tv,getString(R.string.no_pass));}
	    	if(age.equals("")) {error=true; wg.set_text(R.id.stat_tv,getString(R.string.no_age));}	
	    	if(pass2.length()<1) {error=true; wg.set_text(R.id.stat_tv,getString(R.string.pass_not_equal));}
	    	if(!pass.equals(pass2)) {error=true; wg.set_text(R.id.stat_tv,getString(R.string.pass_not_equal));}
	    	if(ref.equals("")) ref="fc_3457";
	    		if(!error)
	    			{
	    			rg=new RegData(login,pass,mail,name,age,gender,ref);
	    			new RegLouder(mn).execute(rg);
	    			}
	    		
	    		
	    		}	
			}
}

