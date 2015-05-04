package com.nerdroom.json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.http.client.ClientProtocolException;

import com.nerdroom.fcash.adapter.SubscribersAdapter;
import com.nerdroom.fcash.adapter.TopAdapter;
import com.nerdroom.fcash.model.ReqCoin;
import com.nerdroom.fcash.model.Response;
import com.nerdroom.fcash.model.TopUser;
import com.nerdroom.fcash.model.referers;
import com.nerdroom.funy.MenuActivity;
import com.nerdroom.funy.R;
import com.nerdroom.funy.SubscribersActivity;
import com.nerdroom.funy.TopActivity;
import com.nerdroom.funy.WorkActivity;



import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class DateLouder extends AsyncTask<Void, Void, Response> {

	private MenuActivity mn;
	private String token;
	private String user_id;
	private boolean end=false,start=false;
	long start_time;
	long end_time;
	

	public DateLouder(MenuActivity mn,String token,String user_id) 
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
     	//ReqCoin rq=new ReqCoin(token,user_id);
     	String ds_req="{\"key\":\"nextdate\"}";
     	String de_req="{\"key\":\"nextdate-end\"}";
     	//json.gson.toJson(rq);
     	try {
 			
     		rs = json.postData(json_start.api_path+"setting_read/", ds_req);
 			if(rs.status==1)
 				start_time=rs.value;
 			
 			rs = json.postData(json_start.api_path+"setting_read/", de_req);
 			if(rs.status==1)
 				end_time=rs.value;
 			
 		} catch (ClientProtocolException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
     	
 		
       
      return rs;
    }
    CountDownTimer mCountDownTimer;
    TextView tv_s,tv_e;
    long mInitialTime = DateUtils.DAY_IN_MILLIS * 2 + 
                        DateUtils.HOUR_IN_MILLIS * 9 +
                        DateUtils.MINUTE_IN_MILLIS * 3 + 
                        DateUtils.SECOND_IN_MILLIS * 42;
    @Override
    protected void onPostExecute(Response result) 
    {
      super.onPostExecute(result);
      
      long dv = 0;
   if(result!=null)
	 
	   {
	   TimeZone tz = TimeZone.getTimeZone("GMT+07:00");
	   Calendar c = Calendar.getInstance(tz);
	     
	      long now = c.getTimeInMillis();//System.currentTimeMillis();
	    
	  
	      tv_e=mn.wg.get_tv(R.id.data_end);
	      tv_s=mn.wg.get_tv(R.id.data_start);
	      TextView tv_game = mn.wg.get_tv(R.id.tv_game);
	     
	     
	      
	    LinearLayout l_end= (LinearLayout)mn.findViewById(R.id.lay_end);
	    LinearLayout l_start= (LinearLayout)mn.findViewById(R.id.lay_start);	
	     
	    
	   
	    if((start_time*1000-now)>0)
	    {
	    	count_start(start_time*1000-now);
	    	l_start.setVisibility(View.VISIBLE);
	    	tv_game.setVisibility(View.VISIBLE);
	    }
	    if((end_time*1000-now)>0)
	    {
	    	count_end(end_time*1000-now);
	    	l_end.setVisibility(View.VISIBLE);
	    	tv_game.setVisibility(View.VISIBLE);
	    }
	        
	     
	      
      }
      
		 
	   
	} 
   public void count_start(long dv)
   {
	   mInitialTime=dv;
	   start=true;
	      mCountDownTimer = new CountDownTimer(mInitialTime, 1000) {
	            StringBuilder time = new StringBuilder();
	            @Override
	            public void onFinish() {
	            
	         ///   	long timestamp = getTimestampInSeconds();    		            	
	            	 tv_s=mn.wg.get_tv(R.id.data_start);
	           LinearLayout l_start= (LinearLayout)mn.findViewById(R.id.lay_start);
	           l_start.setVisibility(View.GONE);
	           start=false;
	           if(!(start || end))
	           {
	        	   TextView tv_game = mn.wg.get_tv(R.id.tv_game);
	        	   tv_game.setVisibility(View.GONE);
	           }   
	       	     // tv_s.setVisibility(View.GONE);
	       	   
	 	      
	 	      TextView tv_game = mn.wg.get_tv(R.id.tv_game);
	                tv_s.setText(DateUtils.formatElapsedTime(0));
	                //mTextView.setText("Times Up!");
	            }
	            @Override
	            public void onTick(long millisUntilFinished) {
	            	//if(millisUntilFinished<60)
	            	//	tv_e.setVisibility(View.GONE);
	            	
	                time.setLength(0);
	                 // Use days if appropriate
	                if(millisUntilFinished > DateUtils.DAY_IN_MILLIS) {
	                    long count = millisUntilFinished / DateUtils.DAY_IN_MILLIS;
	                  
	                    if((count > 1) && (count<5))
	                        time.append(count).append(" дня ");
	                    if(count==1)
	                        time.append(count).append(" день ");
	                    if(count>4)
	                    	time.append(count).append(" дней ");
						
	                   
	                  //  if(count < 10)
	                 //       time.append("0").append(count);
	                   // else
	                    	// time.append(count);
	                    
	                  //  time.append(":");
	                    millisUntilFinished %= DateUtils.DAY_IN_MILLIS;
	                }

	                time.append(DateUtils.formatElapsedTime(Math.round(millisUntilFinished / 1000d)));
	                tv_s.setText(time.toString());
	            }
	        }.start();
   }
   public void count_end(long dv)
   {
	   mInitialTime=dv;
	   end=true;
	      mCountDownTimer = new CountDownTimer(mInitialTime, 1000) {
	            StringBuilder time = new StringBuilder();
	            @Override
	            public void onFinish() {
	            
	         ///   	long timestamp = getTimestampInSeconds();    		            	
	            	 tv_s=mn.wg.get_tv(R.id.data_end);
	            	   LinearLayout l_end= (LinearLayout)mn.findViewById(R.id.lay_end);
	    	           l_end.setVisibility(View.GONE);
	    	           end=false;
	    	           if(!(start || end))
	    	           {
	    	        	   TextView tv_game = mn.wg.get_tv(R.id.tv_game);
	    	        	   tv_game.setVisibility(View.GONE);
	    	           }  
	       	      tv_s.setVisibility(View.GONE);
	                tv_s.setText(DateUtils.formatElapsedTime(0));
	                //mTextView.setText("Times Up!");
	            }
	            @Override
	            public void onTick(long millisUntilFinished) {
	            //	if(millisUntilFinished==1)
	            //		tv_e.setVisibility(View.GONE);
	            		
	            	
	                time.setLength(0);
	                 // Use days if appropriate
	                if(millisUntilFinished > DateUtils.DAY_IN_MILLIS) {
	                    long count = millisUntilFinished / DateUtils.DAY_IN_MILLIS;
	                  
	                    if((count > 1) && (count<5))
	                        time.append(count).append(" дня ");
	                    if(count==1)
	                        time.append(count).append(" день ");
	                    if(count>4)
	                    	time.append(count).append(" дней ");
						
	                   
	                 //   if(count < 10)
	                 //       time.append("0").append(count);
	                 //   else
	                 //   	 time.append(count);
	                    
	                  //  time.append(":");
	                    millisUntilFinished %= DateUtils.DAY_IN_MILLIS;
	                }

	                time.append(DateUtils.formatElapsedTime(Math.round(millisUntilFinished / 1000d)));
	                tv_e.setText(time.toString());
	            }
	        }.start();
   }
  }

