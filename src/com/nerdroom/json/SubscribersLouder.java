package com.nerdroom.json;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;

import com.nerdroom.fcash.adapter.SubscribersAdapter;
import com.nerdroom.fcash.adapter.TopAdapter;
import com.nerdroom.fcash.model.ReqCoin;
import com.nerdroom.fcash.model.Response;
import com.nerdroom.fcash.model.TopUser;
import com.nerdroom.fcash.model.referers;
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

public class SubscribersLouder extends AsyncTask<Void, Void, Response> {

	private SubscribersActivity mn;
	private String token;
	private String user_id;
	json_start json;

	public SubscribersLouder(SubscribersActivity mn) 
	{
	this.mn=mn;
	com.nerdroom.fcash.help.Account ac;
	ac = new com.nerdroom.fcash.help.Account();
	ac.restore(mn);
	this.token=ac.token;
	this.user_id=ac.user_id;
	
	}
	
    @Override
    protected void onPreExecute() {
      super.onPreExecute();
    
    }

    @Override
    protected Response doInBackground(Void... params) {
    	  json= new json_start(mn);	
     	
     	Response rs=null;
     	ReqCoin rq=new ReqCoin(token,user_id);
     	String j_req=json.gson.toJson(rq);
     	try {
     		String path=json_start.api_path+"subscribers/";
 			rs = json.postData(path, j_req);
 			
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
    protected void onPostExecute(Response result) 
    {
      super.onPostExecute(result);
   if(result!=null)
	   if(result.referers!=null)
		   if(result.count!=0)
	   {
      ArrayList<referers> top_list=new ArrayList<referers>();
      int i=0;
      while(i<result.referers.length)
      {
    	referers r = json.gson.fromJson(result.referers[i],referers.class);
      top_list.add(r);
      i++;
      }
     
		  SubscribersAdapter adapter = new SubscribersAdapter(mn, top_list);	
	     ListView list_m = (ListView) mn.findViewById(R.id.top_list);
	     list_m.setAdapter(adapter);
	   }
   if(result.count==0)
   {
 	  TextView tv=mn.wg.get_tv(R.id.tv_err);
 	  tv.setVisibility(View.VISIBLE);
   }
	} 
    
  }

