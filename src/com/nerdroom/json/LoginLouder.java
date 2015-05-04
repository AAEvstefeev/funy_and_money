package com.nerdroom.json;


import java.io.IOException;
import java.util.ArrayList;

import com.nerdroom.fcash.help.MyActivity;
import com.nerdroom.fcash.model.RegData;
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

public class LoginLouder extends AsyncTask<RegData, Void, String> {
MyActivity mn;
	public LoginLouder(MyActivity _mn) 
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
    String res=json.login(params[0]);
    if(!res.contains("password combination is not exists"))
      {
    //	mn.sh.save_reg_data(params[0]);
    	
    	if(res.contains("Login Successfull"))
    		return res;
    	else
    	return mn.getString(R.string.error_service);
      }
    else return mn.getString(R.string.wrong_login_pass);
    }

    protected void onPostExecute(String result) {
      super.onPostExecute(result);
      mn.wg.set_gone(R.id.bar);
	if(result.contains("Login Successfull"))
      {
    	 mn.finish();
    	 Intent i = new Intent(mn,LkActivity.class);
    	 mn.startActivity(i);
      }
	else
		{
		mn.wg.set_text(R.id.stat_tv,result);
		}
	 mn.wg.set_gone(R.id.bar); 		
    } 
    
  }

