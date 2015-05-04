package com.nerdroom.json;


import java.io.IOException;
import java.util.ArrayList;

import com.nerdroom.fcash.help.Account;
import com.nerdroom.fcash.help.MyActivity;
import com.nerdroom.fcash.help.md5;
import com.nerdroom.fcash.model.RegData;
import com.nerdroom.funy.LkActivity;
import com.nerdroom.funy.WorkActivity;
import com.nerdroom.funy.R;





import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

public class RegLouder extends AsyncTask<RegData, Void, String> {
MyActivity mn;
RegData rg;
	public RegLouder(MyActivity _mn) 
	{
	mn=_mn;	
	}
	
    protected void onPreExecute() {
    	  super.onPreExecute();
      mn.wg.set_visible(R.id.bar);
      mn.loud=true;
    }

    protected String doInBackground(RegData... params) {
    json_start json = new json_start(mn);	
    String st=json.reg(params[0]);
    if(st.contains("successfully created"))
      {
    	//mn.sh.save_reg_data(params[0]);
    	/**
    	Account ac = new Account(); 
    	ac.restore(mn);
    	rg=new RegData();
    	rg.email=ac.email;
    	rg.password=ac.password;
    	**/
    	return st;
      }
    else return st;
    }

    protected void onPostExecute(String result) {
      super.onPostExecute(result);
      mn.wg.set_gone(R.id.bar);
	if(result.contains("successfully created"))
      {
		ScrollView scr=(ScrollView)mn.findViewById(R.id.scrollView1);
		scr.setVisibility(View.GONE);
		LinearLayout ll=(LinearLayout)mn.findViewById(R.id.ll);
		ll.setVisibility(View.VISIBLE);
		Button btn_back=mn.wg.get_b(R.id.btn_back);
		 btn_back.setOnClickListener(new View.OnClickListener() 
	    {
	    	public void onClick(View v) 
	    	{
	    		mn.finish();	
	     } });
    //	new LoginLouder(mn).execute(rg);
		//mn.finish();
    	// Intent i = new Intent(mn,LkActivity.class);
    	 //mn.startActivity(i);
      }
	else
		{
		if(result.contains("User with this E-mail is already exists"))
		{
			mn.wg.set_text(R.id.stat_tv,mn.getString(R.string.mail_conflict));	
		}
		else
		mn.wg.set_text(R.id.stat_tv,result);
		}
	 mn.wg.set_gone(R.id.bar); 		
    } 
  }

