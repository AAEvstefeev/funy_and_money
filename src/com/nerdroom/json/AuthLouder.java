package com.nerdroom.json;


import java.io.IOException;
import java.util.ArrayList;

import com.nerdroom.fcash.help.Account;
import com.nerdroom.fcash.help.MyActivity;
import com.nerdroom.fcash.model.RegData;
import com.nerdroom.funy.LkActivity;
import com.nerdroom.funy.R;
import com.nerdroom.funy.WorkActivity;



import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AuthLouder extends AsyncTask<RegData, Void, Boolean> {
MyActivity mn;
	public AuthLouder(WorkActivity _mn) 
	{
	mn=_mn;	
	}
	
    protected void onPreExecute() {
    	  super.onPreExecute();
      //mn.wg.set_visible(R.id.bar);
      //mn.loud=true;
    	  
    }

    protected Boolean doInBackground(RegData... params) {
    json_start json = new json_start(mn);	
    Account ac =new Account();
    ac.restore(mn);
    RegData rg=null;
    if(ac.email!=null && ac.password!=null)
    { rg = new RegData(ac.password,ac.email);
  //  if(json.login(rg))
      {
    //	mn.sh.save_reg_data(params[0]);
    	return true;
      }
   // else return false;
    }
    else return false;
    }

    protected void onPostExecute(Boolean result) {
      super.onPostExecute(result);
     // mn.wg.set_gone(R.id.bar);
    if(result)
    {
      WorkActivity.get_money();
    }
      
    } 
  }

