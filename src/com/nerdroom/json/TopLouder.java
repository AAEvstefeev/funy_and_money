package com.nerdroom.json;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;

import com.nerdroom.fcash.adapter.TopAdapter;
import com.nerdroom.fcash.model.ReqCoin;
import com.nerdroom.fcash.model.Response;
import com.nerdroom.fcash.model.TopUser;
import com.nerdroom.funy.R;
import com.nerdroom.funy.TopActivity;
import com.nerdroom.funy.WorkActivity;



import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class TopLouder extends AsyncTask<Void, Void, Response> {

	private TopActivity mn;
	private String token;
	private String user_id;

	public TopLouder(TopActivity mn) 
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
    protected Response doInBackground(Void... params) {
    	 json_start json= new json_start(mn);	
     	
     	Response rs=null;
     	try {
     		String path=json_start.api_path+"top/";
 			rs = json.postData(path, "{}");
 			
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
	   if(result.top_list!=null)
	   {
      ArrayList<TopUser> top_list=new ArrayList<com.nerdroom.fcash.model.TopUser>();
      int i=0;
      while(i<result.top_list.length)
      {
      top_list.add(new TopUser(result.top_list[i].user_id,result.top_list[i].name,Integer.parseInt(result.top_list[i].total)));
      i++;
      }   
	     TopAdapter adapter = new TopAdapter(mn, top_list);	
	     ListView list_m = (ListView) mn.findViewById(R.id.top_list);
	     list_m.setAdapter(adapter);
	   }
	} 
    
  }

