package com.nerdroom.json;


import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.widget.AbsListView.OnScrollListener;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.nerdroom.fcash.adapter.MenuAdapter;
import com.nerdroom.fcash.adapter.WorkAdapter;
import com.nerdroom.fcash.help.MyActivity;
import com.nerdroom.fcash.model.MyMenuItem;
import com.nerdroom.fcash.model.RegData;
import com.nerdroom.fcash.model.Response;
import com.nerdroom.fcash.model.articles;
import com.nerdroom.fcash.model.categories;
import com.nerdroom.funy.HelpActivity;
import com.nerdroom.funy.LkActivity;
import com.nerdroom.funy.LoginRegActivity;
import com.nerdroom.funy.WorkActivity;
import com.nerdroom.funy.R;
import com.nerdroom.funy.TopActivity;




import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class WorkLouder extends AsyncTask<Void, Void, Boolean> {
WorkActivity mn;
String url;

WorkAdapter adapter;
articles[] ar_cat;
int id;
static int amount;
static int id_art;
	public WorkLouder(WorkActivity _mn, String url, int id) 
	{
	mn=_mn;	
	this.url=url;
	this.id=id;
	if(id>=0)id_art=id;
	}
	
    protected void onPreExecute() {
    	  super.onPreExecute();
    	  
    //  mn.wg.set_visible(R.id.bar);
    //  mn.loud=true;
    	 // mn.setContentView(R.layout.loud_screen);
    }

    protected Boolean doInBackground(Void... params) {
    	 int from,to;
    switch(id)
    {
    case -100:
    	Log.d("Louder","-100");
    	 downloud(0,1);
    	   from=amount-100;
    	   id_art=amount-100;
    	   id=amount-100;
    	   if(id<0)
    	   {id=0;from=0;id_art=0;} 
    	   mn.sh.save_art_number(url, amount-1);
    	    to=100;
    	    
    	    Log.d("from",Integer.toString(from));
    	    Log.d("id",Integer.toString(id));
    	    Log.d("id_art",Integer.toString(id_art));
    	    Log.d("Louder","+100");
    	   if(to>0)
    		   	   downloud(from,to);
    		Log.d("amount",Integer.toString(amount));
    	   
    	    break;
    case -1:
    	Log.d("Louder","-1");
   from=0; 	
    to=id_art-100;
    
    Log.d("from",Integer.toString(from));
    Log.d("id",Integer.toString(id));
    Log.d("id_art",Integer.toString(id_art));
   if(to>0)
	   downloud(from,to);
	Log.d("amount",Integer.toString(amount));
    break;
    case -2:
    	Log.d("Louder","-2");
    from=id_art+100;
    to=amount-from;
    Log.d("from",Integer.toString(from));
    Log.d("id",Integer.toString(id));
    Log.d("id_art",Integer.toString(id_art));
    if(to>0)
    downloud(from,to);
	Log.d("amount",Integer.toString(amount));
        break;
     
    default:   
    	Log.d("Louder","def");
      to=200;
 	 from=id-100;
 	if(from<0)from=0;
    Log.d("from",Integer.toString(from));
    Log.d("id",Integer.toString(id));
    Log.d("id_art",Integer.toString(id_art));
 	 downloud(from,to);
 	Log.d("amount",Integer.toString(amount));
 	break;
 
    }
   
      
   if(WorkActivity.r!=null)
    	return true;
   else 
	   return false;
 
    }

  

	protected void onPostExecute(Boolean result) {
      super.onPostExecute(result);
      //mn.wg.set_gone(R.id.bar);
    //  mn.setContentView(R.layout.work_activity);
    	
      if(result)
      {
    	if(id>=0 || id==-100)  WorkActivity.set_data(); 
    	  
      }
	//	mn.wg.set_text(R.id.stat_tv,"ошибка");
		
	// mn.wg.set_gone(R.id.bar); 		
} 


	  private void downloud(int from,int to) {
	    	 json_start json = new json_start(mn);
			    int in=0;
			 String res=null;
			 ar_cat= new articles[to];
		    	
	    	 {
	    		 
	    		    try {
	    		    	JSONArray cat = null;
	    		    	
	    		    	String req="{\"category_id\":"+url+",\"index_from\":"+from+",\"count_to\":"+to
	    		    			+"}";    		
	    		    	
	    				res=json.postDataString(json_start.api_path+"articles/",req);
	    				
	    				JSONObject jsobj;
	    				JSONArray cat_obj = null;
	    				String[] arts = null;
	    				try {
	    					jsobj = new JSONObject(res);
	    					cat_obj = jsobj.getJSONArray("articles");
	    					amount = jsobj.getInt("amount");
	    					arts = json.gson.fromJson(cat_obj.toString(), String[].class);
	    					if(WorkActivity.r==null)
	    					WorkActivity.r= new articles[amount];
	    				} catch (JSONException e) {
	    					// TODO Auto-generated catch block
	    					e.printStackTrace();
	    				}
	    				
	    		int i=0;
	    				int j=from;
	    				int last=from+to;
	    				if(last>amount)last=amount;
	    				while(last>j && i<ar_cat.length && i<arts.length)
	    				{
	    					ar_cat[i]=json.gson.fromJson(arts[i], articles.class);
	    				 i++;
	    				 j++;
	    				}
	    			} catch (ClientProtocolException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			} catch (IOException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    		    in++;
	    		    }
	    		    while(res==null && in>50);
	    		    
	    		    if(ar_cat==null)mn.finish();
	    		    
	    		    if(ar_cat!=null)
	    		      {
	    		    	int j=0;
	    		    	int number=0;
	    		    	if(from+to<amount)
	    		    		number=from+to;
	    		    	else
	    		    		number=amount;
	    		    	
	    		    	for(int i=from;i<number;i++)
	    		    	{
	    		    	WorkActivity.r[i]=ar_cat[j]; 
	    		    	j++;
	    		    	}
	    		      }	
		}	
	
	
    
}

