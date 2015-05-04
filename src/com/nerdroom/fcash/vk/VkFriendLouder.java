package com.nerdroom.fcash.vk;


import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.nerdroom.fcash.adapter.MenuAdapter;
import com.nerdroom.fcash.adapter.VkAdapter;
import com.nerdroom.fcash.help.MyActivity;
import com.nerdroom.fcash.help.VK_User;
import com.nerdroom.fcash.model.MyMenuItem;
import com.nerdroom.fcash.model.RegData;
import com.nerdroom.fcash.model.Response;

import com.nerdroom.funy.HelpActivity;
import com.nerdroom.funy.LkActivity;
import com.nerdroom.funy.LoginRegActivity;
import com.nerdroom.funy.R;
import com.nerdroom.funy.TopActivity;
import com.perm.kate.api.User;




import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class VkFriendLouder extends AsyncTask<VK_User, Bitmap, Bitmap> {
	ImageView mn;
String url;
ArrayList<User> list;
ListView list_m ;
ProgressBar bar;
String path;
View ctx;

	public VkFriendLouder(ImageView _mn,View ctx) 
	{
	mn=_mn;	
	this.ctx=ctx;
	this.path = mn.getTag().toString();
	//bar=mn.wg.get_bar(R.id.bar);
	bar= (ProgressBar) ctx.findViewById(R.id.bar);
	}
	
    protected void onPreExecute() {
    	  super.onPreExecute();
      mn.setVisibility((View.VISIBLE));
    //	bar.setVisibility(View.VISIBLE);  
    	 Drawable myImage = ctx.getResources().getDrawable(R.drawable.ic_launcher);
    	mn.setImageDrawable(myImage);  
    //  mn.loud=true;
    	 
    	
    	  
    }

    protected Bitmap doInBackground(VK_User... params) {
    	 InputStream in=null;
 		try {
 			in = new java.net.URL(params[0].user.photo).openStream();
 		} catch (MalformedURLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
 	
 	Bitmap	bmp = BitmapFactory.decodeStream(in);
 	params[0].photo=bmp;
  return bmp;
    }

    protected void onPostExecute(Bitmap bmp) {
      super.onPostExecute(bmp);
      //mn.wg.set_gone(R.id.bar);
    //  mn.setContentView(R.layout.activity_main);
    // ImageView f = (ImageView) mn.findViewById(R.id.vk_foto);
   
     if (mn.getTag().toString().equals(path))
     {
         /* The path is not same. This means that this
            image view is handled by some other async task. 
            We don't do anything and return. */
    	 if(bmp != null && mn != null){
    	      mn.setVisibility(View.VISIBLE);
    	      mn.setImageBitmap(bmp);
    	    //  bar.setVisibility(View.GONE);  
    	  }
    	 
         return;
  }
//if(path.equals(mn.getTag().toString()))
 
	//	mn.wg.set_text(R.id.stat_tv,"ошибка");
		
	// mn.wg.set_gone(R.id.bar); 		
} 

    
    
   
}

