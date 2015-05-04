package com.nerdroom.json;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;

import com.nerdroom.fcash.help.Account;
import com.nerdroom.fcash.help.MyActivity;
import com.nerdroom.fcash.model.Response;
import com.nerdroom.funy.LkActivity;
import com.nerdroom.funy.R;




import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LkLouder extends AsyncTask<Void, Void, Void> {
MyActivity mn;
Response rs=null;
RelativeLayout l;
ProgressBar bar;
Account ac;
TextView name,rcode,level;
	public LkLouder(MyActivity mn) 
	{
		this.mn=mn;
	}
	
    @Override
    protected void onPreExecute() {
      super.onPreExecute();
    rcode=mn.wg.get_tv(R.id.rcode);
    name=mn.wg.get_tv(R.id.name);
    l=(RelativeLayout)mn.findViewById(R.id.main_layout);
    bar=(ProgressBar)mn.findViewById(R.id.bar);
    l.setVisibility(View.GONE);
    bar.setVisibility(View.VISIBLE);
    }

    @Override
    protected Void doInBackground(Void... params) {
    json_start js = new json_start(mn);	
    ac = new Account(); 
	ac.restore(mn);
	 
	
    try {
    	if(ac.user_id!=null);
    	String st="{\"user_id\":"+"\""+ac.user_id+"\"}";
    	Log.i("tag", st);
		rs=js.postData(js.api_path+"user_info/", st);
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
     if(rs!=null)
     {
    	 if(rs.name!=null) 
    	{
    	
    		 name.setText(rs.name);
    		 LkActivity.name=rs.name;
    	}
    	if(rs.referer_code!=null) 
    	{
    		rcode.setText("код:"+rs.referer_code);
    		ac.ref=rs.referer_code;
    		ac.save(mn);
    	}
     }
     l.setVisibility(View.VISIBLE);
     bar.setVisibility(View.GONE);	 
     
	   		} 
    
  }

