package com.nerdroom.json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.http.client.ClientProtocolException;

import com.nerdroom.fcash.adapter.SubscribersAdapter;
import com.nerdroom.fcash.adapter.TopAdapter;
import com.nerdroom.fcash.help.Constanta;
import com.nerdroom.fcash.model.ReqCoin;
import com.nerdroom.fcash.model.Response;
import com.nerdroom.fcash.model.ResponseSetting;
import com.nerdroom.fcash.model.TopUser;
import com.nerdroom.fcash.model.referers;

import com.nerdroom.funy.MenuActivity;
import com.nerdroom.funy.R;
import com.nerdroom.funy.SubscribersActivity;
import com.nerdroom.funy.TopActivity;
import com.nerdroom.funy.WorkActivity;



import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class VersionLouder extends AsyncTask<Void, Void, ResponseSetting> {

	private MenuActivity mn;
	private String token;
	private String user_id;
	private String version;
	

	public VersionLouder(MenuActivity mn,String token,String user_id) 
	{
	this.mn=mn;
	this.token=token;
	this.user_id=user_id;
	
	}
	
    @Override
    protected void onPreExecute() {
      super.onPreExecute();
    
    }

    @Override
    protected ResponseSetting doInBackground(Void... params) {
    	 json_start json= new json_start(mn);	
     	
     	ResponseSetting rs=null;
     	//ReqCoin rq=new ReqCoin(token,user_id);
     	String ds_req="{\"key\":\"android-version\"}";
     
     	//json.gson.toJson(rq);
     	try {
 			
     	String	rs_st = json.postDataString(json_start.api_path+"setting_read/", ds_req);
     	if(rs_st!=null)
     		{
     		rs=json.gson.fromJson(rs_st, ResponseSetting.class);
     		if(rs.status==1)
 				version=rs.value;
     		}
 			
 			
 		
 			
 		} catch (ClientProtocolException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
     	
 		
       
      return rs;
    }

    @Override
    protected void onPostExecute(ResponseSetting result) 
    {
      super.onPostExecute(result);
  if(version!=null)
  	{
	 if(!Constanta.version.equals(version)) 
	  {
		 showCallDialog();
	  }
  	}
      
	} 

    
	private void showCallDialog() {
		String title=mn.getString(R.string.new_ver_title);
		String shure=mn.getString(R.string.new_ver_shure);;
	
		
		
		AlertDialog alert = new AlertDialog.Builder(mn)
				.create();
		alert.setTitle(title);

		alert.setMessage(shure);

		alert.setButton(mn.getString(R.string.no), new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
	
	
			alert.setButton2(mn.getString(R.string.yes), new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.nerdroom.funy");
					  Intent intent = new Intent (Intent.ACTION_VIEW, uri); 
					  mn.startActivity(intent);
					
				}
			});
		alert.show();
	}   
    

    
}

