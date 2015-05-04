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

public class GetMoneyLouder extends AsyncTask<Void, Integer, Integer> {

	private WorkActivity mn;
	private String token;
	private String user_id;
	

	public GetMoneyLouder(WorkActivity mn, String token, String user_id) 
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
    protected Integer doInBackground(Void... params) {
   json_start json= new json_start(mn);	
    	String req = json.gson.toJson(new ReqCoin(token,user_id));
    	try {
			Response rs = json.postData(json_start.api_path+"balance/", req);
			if(rs!=null)return (int)rs.value;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
      
    }

    @Override
    protected void onPostExecute(Integer result) {
      super.onPostExecute(result);
     TextView c = (TextView) mn.findViewById(R.id.tv_coin);
     c.setText(new Integer(result).toString());
     mn.coin_count=result;
	   		} 
    
  }

