package com.nerdroom.json;


import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;

import com.nerdroom.fcash.help.Account;
import com.nerdroom.fcash.help.MyActivity;
import com.nerdroom.fcash.help.md5;
import com.nerdroom.fcash.model.ChangePass;
import com.nerdroom.fcash.model.RegData;
import com.nerdroom.fcash.model.Response;
import com.nerdroom.funy.LkActivity;
import com.nerdroom.funy.WorkActivity;
import com.nerdroom.funy.R;





import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class ChangePassLouder extends AsyncTask<ChangePass, Void, Response> {
MyActivity mn;
RegData rg;
	public ChangePassLouder(MyActivity _mn) 
	{
	mn=_mn;	
	}
	
    protected void onPreExecute() {
    	  super.onPreExecute();
      mn.wg.set_visible(R.id.bar);
      mn.loud=true;
    }

    protected Response doInBackground(ChangePass... params) {
    json_start json = new json_start(mn);
    ChangePass chs=params[0];
    String pass=chs.new_password;
    chs.new_password=md5.md5(pass);
    Response rs = null;
    String st = null;
	try {
		RegData rg=new RegData();
		
		//String req="{email:\""+params[0]+"\"}";
		String req=json.gson.toJson(chs);
		String path=json.api_path+"change_password_public/";
		st = json.postDataString(path,req);
		rs=json.gson.fromJson(st, Response.class);
	} catch (ClientProtocolException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	if(rs!=null)
		if(rs.status==1)
			{
			Account ac= new Account();
    		ac.restore(mn);
    		ac.password=pass;
    		ac.save(mn);
			}
	return rs;
   /** 
    if(st.contains("successfully created"))
      {
    	//mn.sh.save_reg_data(params[0]);
    	Account ac = new Account(); 
    	ac.restore(mn);
    	rg=new RegData();
    	rg.email=ac.email;
    	rg.password=ac.password;
    	return st;
      }
      
    else return st;
    **/
    }

    protected void onPostExecute(Response result) {
      super.onPostExecute(result);
      mn.wg.set_gone(R.id.bar);
    if(result!=null)  
	if(result.status==1)
      {
		mn.finish();
		Toast toast = Toast.makeText(mn, 
				mn.getString(R.string.succes_change_pass),
				Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
      }
	else
		{
		if(result.text.contains("Incorrect old"))
			mn.wg.set_text(R.id.stat_tv,mn.getString(R.string.incorrect_old));
		else
		mn.wg.set_text(R.id.stat_tv,result.text);
		//else
		//mn.wg.set_text(R.id.stat_tv,mn.getString(R.string.error_service));
		}
    if(result==null)
    	mn.wg.set_text(R.id.stat_tv,mn.getString(R.string.error_service));
	 mn.wg.set_gone(R.id.bar); 		
    } 
  }

