package com.nerdroom.json;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;

import com.nerdroom.fcash.model.ReqCoin;
import com.nerdroom.fcash.model.Response;
import com.nerdroom.funy.R;
import com.nerdroom.funy.WorkActivity;



import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class BannerClickLouder extends AsyncTask<Void, Void, Void> {

	private WorkActivity mn;
	private String token;
	private String user_id;
	

	public BannerClickLouder(WorkActivity mn, String token, String user_id) 
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
    protected Void doInBackground(Void... params) {
    	 json_start json= new json_start(mn);	
     	String req = json.gson.toJson(new ReqCoin(token,user_id));
     	try {
     		
 			json.postData(json.api_path+"banner_click/", req);
 		
 			WorkActivity.coin_count++;
 		} catch (ClientProtocolException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
 		
       
      return null;
    }

    @Override
    protected void onPostExecute(Void result) {
      super.onPostExecute(result);
      TextView tv_coin = mn.wg.get_tv(R.id.tv_coin);
  	tv_coin.setText(Integer.toString(WorkActivity.coin_count));
	   		} 
    
  }

