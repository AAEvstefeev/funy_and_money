package com.nerdroom.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


import java.util.Random;

import com.nerdroom.fcash.help.MyWebView;
import com.nerdroom.funy.BartActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class louder extends AsyncTask<Void, Void, Void> {
	Context ctx;
	String user_id;
	String art_id;
	int count=0;
	MyWebView mn;
	public louder() 
	{
		
	}
	
    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      Log.d("11111111", "11111111");
  
    }

    @Override
    protected Void doInBackground(Void... params) {
    	Log.d("11111111", "2222222");

		//new json_start(this);	
    
      return null;
    }

    @Override
    protected void onPostExecute(Void result) {
      super.onPostExecute(result);
     
	   		} 
    
  }

