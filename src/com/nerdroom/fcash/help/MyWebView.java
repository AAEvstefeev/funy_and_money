package com.nerdroom.fcash.help;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.loopj.android.image.SmartImageView;
import com.nerdroom.fcash.model.RegData;
import com.nerdroom.fcash.model.articles;
import com.nerdroom.fcash.model.categories;

import com.nerdroom.fcash.help.Account;
import com.nerdroom.funy.R;
import com.nerdroom.funy.R.id;
import com.nerdroom.funy.R.layout;
import com.nerdroom.funy.R.styleable;
import com.nerdroom.funy.WorkActivity;
import com.nerdroom.json.MakeLike;
import com.nerdroom.json.SaveImage;
import com.nerdroom.json.WebLouder;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MyWebView extends RelativeLayout {
	
	public TouchImageView web;
	public TouchImageView web2;
	private ProgressBar bar;
	public RelativeLayout layout;

	
	public String url;

	public String art_id;
	public MyWebView mn;
	public String html;
	public String likes;
	private Context ctx;
	
	public ArrayList <String> image_mass;
	public boolean active=false; 
	public MyWebView(Context context) {
		this(context, null);
		ctx=context;
	}
	
	public MyWebView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	
	public MyWebView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		((Activity)getContext())
		.getLayoutInflater()
		.inflate(R.layout.mywebview, this, true);
		
		setUpViews();
		setAttrs(attrs);
	}
	
	private void setUpViews() {
		web = (TouchImageView)findViewById(R.id.img);
	//	web2 = (TouchImageView)findViewById(R.id.img2);
		bar = (ProgressBar)findViewById(R.id.bar);
		layout = (RelativeLayout)findViewById(R.id.layout);
		
	}
	


	private void setAttrs(AttributeSet attrs) {
		if (attrs != null) {
			TypedArray a = getContext()
			.obtainStyledAttributes(attrs, R.styleable.MyWebView, 0,0);
			String CharSequences=a.getString(R.styleable.MyWebView_layout_marginTop);
			
			//LayoutParams lp = new LinearLayout.LayoutParams(layout.LayoutParams.WRAP_CONTENT, layout.LayoutParams.WRAP_CONTENT);
		//	lp.setMargins(left, top, right, bottom);
			a.recycle();
		}
	}

	public void setBarGone() {
		bar.setVisibility(View.GONE);	
	}
	public void setBarVisible() {
		bar.setVisibility(View.VISIBLE);	
	}
	public void setGone() {
		layout.setVisibility(View.GONE);	
		//layout.getLayoutParams().height = 0;
	    //layout.getLayoutParams().width = 0;
		web.setVisibility(View.GONE);
	}
	public void setVisible() {
		layout.setVisibility(View.VISIBLE);
		//layout.getLayoutParams().height = 400;
	    //layout.getLayoutParams().width = 400;
		web.setVisibility(View.VISIBLE);	
	}
	public void loadUrl(articles c)  {
		//web.setWebViewClient(new myWebClient());
url=c.img;

	//
	//	this.url=c.link;
		this.art_id=c.id;
		this.html=c.text;
		this.likes=c.likes;
	//	tv_like.setText(new Integer(this.likes).toString());
		this.image_mass=new ArrayList <String>();
		this.image_mass.add(c.img);
		setBarVisible();
		mn=this;
	//	new WebLouder(this).execute();
	//	SmartImageView myImage = (SmartImageView) this.findViewById(R.id.img2);
	//	myImage.setImageUrl(url);
  	loud_image();
	
 
		
	
		
	}

	private void loud_image() {
		// TODO Auto-generated method stub
		  Ion.with(web).load(url).setCallback(new FutureCallback<ImageView>() {
		  	    @Override  	
				public void onCompleted(Exception e, ImageView result) {
					// TODO Auto-generated method stub
		  	      web.setVisibility(View.VISIBLE);
		  	   
		  		if(active)  WorkActivity.tv_like.setText(likes);
		  		setBarGone(); 	
				}
		  	}); 
	}

	public void setColor(int color) {
			layout.setBackgroundColor(color);	
	}


	


	  
	    

	  
	



}
