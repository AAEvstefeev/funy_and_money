package com.nerdroom.json;


import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nerdroom.fcash.adapter.MenuAdapter;
import com.nerdroom.fcash.help.GIFView;
import com.nerdroom.fcash.help.MyActivity;
import com.nerdroom.fcash.model.MyMenuItem;
import com.nerdroom.fcash.model.RegData;
import com.nerdroom.fcash.model.Response;
import com.nerdroom.fcash.model.categories;
import com.nerdroom.funy.HelpActivity;
import com.nerdroom.funy.LkActivity;
import com.nerdroom.funy.LoginRegActivity;
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
import android.widget.TextView;

public class MenuLouder extends AsyncTask<String, Void, Boolean> {
MyActivity mn;
String url;
ArrayList<MyMenuItem> menu;
GIFView bar;
ListView list_m ;
	public MenuLouder(MyActivity _mn) 
	{
	mn=_mn;	
	bar=(GIFView) mn.findViewById(R.id.bar);
	list_m = (ListView) mn.findViewById(R.id.menu_list);
	
	}
	
    protected void onPreExecute() {
    	  super.onPreExecute();
    //  mn.wg.set_visible(R.id.bar);
    //  mn.loud=true;
    //	  mn.setContentView(R.layout.loud_screen);
    	  bar.setVisibility(View.VISIBLE);
    	  list_m.setVisibility(View.GONE);
    	  TextView tv_error = (TextView) mn.findViewById(R.id.tv_error);
    	  tv_error.setVisibility(View.GONE);
    	  
    }

    protected Boolean doInBackground(String... params) {
    json_start json = new json_start(mn);
    int in=0;
    String res=null;
    
    ArrayList<categories> ar_cat= new ArrayList<categories>();
    {
    try {
		res=json.postDataString(params[0],"{}");
		JSONObject jsobj;
    	
		try {
			
			jsobj = new JSONObject(res);
			JSONArray cat = jsobj.getJSONArray("categories");
	int i=0;
			while(cat.length()>i)
			{
				String c=(String) cat.get(i);
			 ar_cat.add(json.gson.fromJson(c, categories.class));
			 i++;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//r=json.connect(params[0]);
	} catch (ClientProtocolException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    in++;
    }
   // while(res==null && in>50);
    
   // if(res==null)mn.finish();
    if(res!=null)
      {
    	
    	//Response r=json.gson.fromJson(res, Response.class); 
    	int i=0;
    	menu = new ArrayList<MyMenuItem>();
    	while(i<ar_cat.size())
    	{
    		
    		
    			menu.add(new MyMenuItem(ar_cat.get(i)));
    		
    	i++;
    	}
    	
    	return true;
      }
    else return false;
    }

    protected void onPostExecute(Boolean result) {
      super.onPostExecute(result);
      //mn.wg.set_gone(R.id.bar);
    //  mn.setContentView(R.layout.activity_main);
      ListView list_m = (ListView) mn.findViewById(R.id.menu_list);
      TextView tv_error = (TextView) mn.findViewById(R.id.tv_error);
      if(result)
      {
      MenuAdapter adapter = new MenuAdapter(mn, menu);	
	     
	     
	     list_m.setAdapter(adapter);
	    
		  list_m.setVisibility(View.VISIBLE);
		  tv_error.setVisibility(View.GONE);
      }
      else 
      {
    	  list_m.setVisibility(View.GONE);
    	  tv_error.setVisibility(View.VISIBLE);
      }
      bar.setVisibility(View.GONE);
	//	mn.wg.set_text(R.id.stat_tv,"ошибка");
		
	// mn.wg.set_gone(R.id.bar); 		
} 

    
    
   
}

