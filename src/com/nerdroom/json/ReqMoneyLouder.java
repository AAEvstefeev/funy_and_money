package com.nerdroom.json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.http.client.ClientProtocolException;

import com.nerdroom.fcash.adapter.SubscribersAdapter;
import com.nerdroom.fcash.adapter.TopAdapter;
import com.nerdroom.fcash.help.Account;
import com.nerdroom.fcash.help.MyActivity;
import com.nerdroom.fcash.model.ReqCoin;
import com.nerdroom.fcash.model.ReqMoney;
import com.nerdroom.fcash.model.Response;
import com.nerdroom.fcash.model.ResponseSetting;
import com.nerdroom.fcash.model.TopUser;
import com.nerdroom.fcash.model.referers;
import com.nerdroom.funy.MenuActivity;
import com.nerdroom.funy.R;
import com.nerdroom.funy.SubscribersActivity;
import com.nerdroom.funy.TopActivity;
import com.nerdroom.funy.WorkActivity;



import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class ReqMoneyLouder extends AsyncTask<Void, Void, Void> {

	private MyActivity mn;
	private String token;
	private String user_id;
	private int type_id;
	
	private int balance_1=0;
	private int balance_2=0;
	private String kurs=null;
	private int status=-1;
	private String text=null;
	private String req_id=null;
	Account ac;
	String url;
	ReqMoney rm;
	private Response rs;

	public ReqMoneyLouder(MyActivity mn,String url,String accaunt,String value,String type) 
	{
	this.mn=mn;
	
	ac=new Account();
	ac.restore(mn);
	this.user_id=ac.user_id;
	this.token=ac.token;
	this.url=url;
	rm=new ReqMoney(this.token,this.user_id,accaunt,type,Integer.valueOf(value));
	}
	public ReqMoneyLouder(MyActivity mn,String url) 
	{
	this.mn=mn;
	this.url=url;
	ac=new Account();
	ac.restore(mn);
	this.user_id=ac.user_id;
	this.token=ac.token;
	rm=new ReqMoney(this.token,this.user_id);
	
	}
	public ReqMoneyLouder(MyActivity mn,String url, String req_id) 
	{
	this.mn=mn;
	this.url=url;
	ac=new Account();
	ac.restore(mn);
	this.user_id=ac.user_id;
	this.token=ac.token;
	rm=new ReqMoney(this.token,this.user_id,req_id);
	
	}
    @Override
    protected void onPreExecute() {
      super.onPreExecute();
    
    }

    @Override
    protected Void doInBackground(Void... params) {
    	 json_start json= new json_start(mn);	
   
     	Response rs=null;
     	//ReqCoin rq=new ReqCoin(token,user_id);
     //	String ds_req="{\"key\":\"nextdate\"}";
     //	String de_req="{\"key\":\"nextdate-end\"}";
     	String req=json.gson.toJson(rm);
     	try {
     		String path=json_start.api_path+url+"/";
 			rs = json.postData(path,req);
 			
 		} catch (ClientProtocolException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
     	
     	
    this.rs=rs;
     
    
		return null;
       
      
    }

    @Override
    protected void onPostExecute(Void result) 
    {
      super.onPostExecute(result);
      

if(url.equals("request_create"))
			create();
if(url.equals("request_delete"))
	delete();			
if(url.equals("request_user"))
	get_req();			 
	   
	}
	private void get_req() {
		// TODO Auto-generated method stub
		
	}
	private void delete() {
		// TODO Auto-generated method stub
		
	}
	private void create() {
		// TODO Auto-generated method stub
		TextView tv_err=mn.wg.get_tv(R.id.tv_error);
		if(rs.status!=1)
			tv_err.setText(rs.text);
		else
			{mn.finish();
			Toast.makeText(mn,mn.getString(R.string.succes_req),Toast.LENGTH_SHORT).show();
			}
	} 
    
  }

