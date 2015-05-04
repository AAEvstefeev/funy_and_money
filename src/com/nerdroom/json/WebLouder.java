package com.nerdroom.json;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.koushikdutta.async.future.Future;
import com.koushikdutta.ion.Ion;
import com.nerdroom.fcash.help.MyWebView;
import com.nerdroom.fcash.model.Response;
import com.nerdroom.fcash.model.req_like;
import com.nerdroom.funy.R;
import com.nerdroom.funy.WorkActivity;




import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class WebLouder extends AsyncTask<Void, Void, Void> {
MyWebView mweb;
Context ctx;
Bitmap bmp=null;
private TextView tv_like;

	public WebLouder(MyWebView mweb) 
	{
	this.mweb=mweb;	
	}
	
    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      mweb.setBarVisible();
    //tv_like=(TextView) WorkActivity.mn.findViewById(R.id.like_number);
      mweb.web.setVisibility(View.GONE);
  	mweb.web.setImageBitmap(null);
    }

    @Override
    protected Void doInBackground(Void... params) {
	
 
	
		
		 mweb.image_mass = new ArrayList <String>();

		
			mweb.image_mass.add(mweb.url);
			boolean ready=false;
			int count=0;
			if(false)
			 if(!mweb.url.contains(".gif"))
			while((!ready) && (count<10))
			{
			count++;
			URL url1 = null;
			
			try {
				url1 = new URL(mweb.url);
				bmp = BitmapFactory.decodeStream(url1.openConnection().getInputStream());
				if(bmp!=null)ready=true;
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			}
	//	 Drawable  image=null;
		 //      if(bmp!=null) image  =  new BitmapDrawable(BitmapFactory.decodeByteArray(bmp, 0, bmp.length));
		  //      mweb.web.setImageDrawable(image); 
				
		if(bmp==null)
			bmp=BitmapFactory.decodeResource(mweb.mn.getResources(), R.drawable.logo);
		        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
      super.onPostExecute(result);
     // if(!mweb.url.contains(".gif"))
      if(false)
      {
    	  mweb.web.setImageBitmap(bmp);
    	  mweb.web.setMaxZoom(4f);
    	  mweb.web.reset();
    	  mweb.web.setVisibility(View.VISIBLE);
      }  
      else 
      {
    	 Future<ImageView> a=null;
		while(a==null)
		{
    	  a=Ion.with(mweb.web).load(mweb.url); 
		}
      }
      mweb.web.setVisibility(View.VISIBLE);
	//if(mweb.active)   tv_like.setText(mweb.likes);
	mweb.setBarGone();
  ///  WorkActivity.sub_menu.setVisibility(View.VISIBLE);
  //  mweb.loadDataWithBaseURL();
	   		} 
    
  }

