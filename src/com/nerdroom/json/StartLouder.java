package com.nerdroom.json;


import java.io.IOException;
import java.util.ArrayList;

import com.nerdroom.fcash.help.Account;
import com.nerdroom.fcash.help.MyActivity;
import com.nerdroom.fcash.model.RegData;
import com.nerdroom.fcash.model.Response;
import com.nerdroom.funy.LkActivity;
import com.nerdroom.funy.R;



import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class StartLouder extends AsyncTask<RegData, Void, String> {
MyActivity mn;
Response rs;
	public StartLouder(MyActivity _mn) 
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
    Account ac=new Account();
    ac.restore(mn);
    RegData r= new RegData(); 
    r.email=ac.email;
    r.password=ac.password;
    String res=json.login(r);
    if(!res.contains("password combination is not exists"))
      {
    //	mn.sh.save_reg_data(params[0]);
    	
    	if(res.contains("Login Successfull"))
    		{return res;}
    	else
    	return mn.getString(R.string.error_service);
      }
    else return mn.getString(R.string.wrong_login_pass);
    }

    protected void onPostExecute(String result) {
      super.onPostExecute(result);
     
	if(result.contains("Login Successfull"))
      {
    	
      }
		
    } 
    
  }

