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
import com.nerdroom.fcash.model.Response;
import com.nerdroom.fcash.model.ResponseSetting;
import com.nerdroom.fcash.model.TopUser;
import com.nerdroom.fcash.model.referers;
import com.nerdroom.funy.GetMoneyActivity;
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

public class Balance2Louder extends AsyncTask<Void, Void, Void> {

	private MyActivity mn;
	private String token;
	private String user_id;
	private int type_id;
	
	private int balance_1=0;
	private int balance_2=0;
	private String kurs=null;
	Account ac;
	

	public Balance2Louder(MyActivity mn) 
	{
	this.mn=mn;
	
	ac=new Account();
	ac.restore(mn);
	this.user_id=ac.user_id;
	this.token=ac.token;
	
	}
	
    @Override
    protected void onPreExecute() {
      super.onPreExecute();
    
    }

    @Override
    protected Void doInBackground(Void... params) {
    	 json_start json= new json_start(mn);	
    int i = 2;
     	Response rs=null;
     	rs=new Response();
     	rs.token=token;
     	rs.user_id=user_id;
     	rs.type_id=i;
     	//ReqCoin rq=new ReqCoin(token,user_id);
     //	String ds_req="{\"key\":\"nextdate\"}";
     //	String de_req="{\"key\":\"nextdate-end\"}";
     	String req=json.gson.toJson(rs);
     	try {
 			rs = json.postData(json_start.api_path+"balance/", req);
 			
 		} catch (ClientProtocolException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
     	
     	if(rs.status==1)
     	{
     	
     		if(i==2)
     			balance_2=(int) rs.value;
     		//if(GetMoneyActivity!=null)
     		try{
     			GetMoneyActivity.balance=balance_2;
     		}catch(NullPointerException e)
     		{
     			
     		}
     		
     	}
     	
     
     
   
		return null;
       
      
    }

    @Override
    protected void onPostExecute(Void result) 
    {
      super.onPostExecute(result);
      

  TextView tv_b2=mn.wg.get_tv(R.id.tv_balance_2);
 
  tv_b2.setText(Integer.toString(balance_2));

      
		 
	   
	} 
    
  }

