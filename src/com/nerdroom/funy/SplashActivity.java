package com.nerdroom.funy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;



public class SplashActivity extends Activity  {
SplashActivity s;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		s=this;
		
	setContentView(R.layout.splash);  
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);	 
		 new Handler().postDelayed(new Runnable(){
	            @Override
	            public void run() {
	                /* Create an Intent that will start the Menu-Activity. */
	            	
	                Intent mainIntent = new Intent(s,com.nerdroom.funy.MenuActivity.class);
	                s.startActivity(mainIntent);
	                s.finish();
	            }
	        }, 3000);
}
	
	
}