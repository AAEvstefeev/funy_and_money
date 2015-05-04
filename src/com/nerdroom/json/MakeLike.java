package com.nerdroom.json;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;

import com.nerdroom.fcash.help.Account;
import com.nerdroom.fcash.help.MyWebView;
import com.nerdroom.fcash.help.md5;
import com.nerdroom.fcash.model.Response;
import com.nerdroom.fcash.model.req_like;
import com.nerdroom.funy.WorkActivity;



import android.content.Context;
import android.os.AsyncTask;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MakeLike extends AsyncTask<Void, Void, Void> {
Context ctx;
String user_id;
String art_id;
int count=0;
MyWebView mn;
	public MakeLike(String user_id, String art_id,MyWebView mn) 
	{
		this.user_id=user_id;
		this.art_id=art_id;
		
		this.mn=mn;
	}
	
    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      
      
    }

    @Override
    protected Void doInBackground(Void... params) {
    json_start json = new json_start(ctx);	
    Log.i("lkActive","Like");
    TelephonyManager telephonyManager = (TelephonyManager)WorkActivity.mn.getSystemService(Context.TELEPHONY_SERVICE);

    String id = telephonyManager.getDeviceId();
    id=md5.md5(id);
	String json_st=null; 
	json_st= json.gson.toJson(new req_like(id,art_id));
	try {
		String path=json.api_path+"article_do_like/";
		Response rs=null;
		
		rs=json.postData(path,json_st);
		
		if(rs.text.contains("successfully liked"))
			count++;
		if(rs.text.contains("disliked"))
			count--;
			
	} catch (ClientProtocolException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
      return null;
    }

    @Override
    protected void onPostExecute(Void result) {
      super.onPostExecute(result);
      int i=(Integer.parseInt(mn.likes)+count);
      mn.likes=Integer.toString(i);
      WorkActivity.tv_like.setText(mn.likes);
   //  mn.tv_like.setText(mn.likes);
      WorkActivity.mAsker.onResume();
	   		} 
    
  }

