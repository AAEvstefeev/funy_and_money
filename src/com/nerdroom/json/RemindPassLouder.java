package com.nerdroom.json;


import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;

import com.nerdroom.fcash.help.Account;
import com.nerdroom.fcash.help.MyActivity;
import com.nerdroom.fcash.help.md5;
import com.nerdroom.fcash.model.RegData;
import com.nerdroom.fcash.model.Response;
import com.nerdroom.funy.LkActivity;
import com.nerdroom.funy.WorkActivity;
import com.nerdroom.funy.R;





import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class RemindPassLouder extends AsyncTask<String, Void, Response> {
MyActivity mn;
RegData rg;
	public RemindPassLouder(MyActivity _mn) 
	{
	mn=_mn;	
	}
	
    protected void onPreExecute() {
    	  super.onPreExecute();
      mn.wg.set_visible(R.id.bar);
      mn.loud=true;
    }

    protected Response doInBackground(String... params) {
    json_start json = new json_start(mn);	
    String st = null;
	try {
		RegData rg=new RegData();
		rg.email=params[0];
		//String req="{email:\""+params[0]+"\"}";
		String req=json.gson.toJson(rg);
		String path=json.api_path+"remind_password/";
		st = json.postDataString(path,req);
	} catch (ClientProtocolException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	Response rs=null;
	if(st!=null)
		rs=json.gson.fromJson(st, Response.class);
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
    {	
	if(result.status==1)
      {
		//("Password was changed and was sent to specified E-mail")
		
		mn.wg.set_text(R.id.stat_tv,mn.getString(R.string.pass_changed));
		Button btn=(Button)mn.findViewById(R.id.btn_login);
		EditText edt=(EditText)mn.findViewById(R.id.edt_mail);
		TextView tv=(TextView)mn.findViewById(R.id.textView1);
		tv.setVisibility(View.GONE);
		edt.setVisibility(View.GONE);
		btn.setText(mn.getString(R.string.back));
		btn.setOnClickListener(new View.OnClickListener() 
	    {
	    	public void onClick(View v) 
	    	{
	    	mn.finish();
	    		
	     } });	
		
      }
	else
		{
		if(result.text.contains("User with specified email not found"))
		{
			mn.wg.set_text(R.id.stat_tv,mn.getString(R.string.mail_not_find));	
		}
		else
		mn.wg.set_text(R.id.stat_tv,mn.getString(R.string.error_service));
		}
    }
    else 
    	mn.wg.set_text(R.id.stat_tv,mn.getString(R.string.error_service));	
    mn.wg.set_gone(R.id.bar); 		
    } 
  }

