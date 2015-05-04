package com.nerdroom.funy;






import java.util.ArrayList;

import ru.wapstart.plus1.sdk.Plus1BannerAsker;
import ru.wapstart.plus1.sdk.Plus1BannerRequest;
import ru.wapstart.plus1.sdk.Plus1BannerView;



import com.nerdroom.fcash.help.MyActivity;
import com.nerdroom.fcash.help.base_url;
import com.nerdroom.fcash.model.Response;
import com.nerdroom.fcash.model.articles;
import com.nerdroom.fcash.vk.Account;
import com.nerdroom.fcash.vk.Constants;
import com.nerdroom.fcash.vk.VKActivity;
import com.nerdroom.fcash.vk.VkFriendsActivity;
import com.nerdroom.json.AuthLouder;
import com.nerdroom.json.BannerClickLouder;
import com.nerdroom.json.GetMoneyLouder;
import com.nerdroom.json.WorkLouder;
import com.perm.kate.api.Api;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.webkit.WebView;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Copy_2_of_WorkActivity extends WorkActivity implements OnClickListener,OnTouchListener
{
	static public	Account account=new Account();
  static public  Api api;
  static  private final int REQUEST_LOGIN=1;
	private Plus1BannerAsker mAsker;
		private Plus1BannerView mBannerView;
		 TextView tv_coin;
		 public static String token;
		 public static String user_id;
		 public int coin_count=0;
		 boolean time_out=false;
		 boolean f_step=true;
		 
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private static final int DELTA1=20;
    static Animation  trans_on_l;
	static Animation trans_off_l;
	static Animation trans_on_r;
	static Animation trans_off_r;
    private GestureDetector gestureDetector;
    static View.OnTouchListener gestureListener;
    MotionEvent me;
    static com.nerdroom.fcash.help.MyWebView web1;
	static com.nerdroom.fcash.help.MyWebView web2;
	static com.nerdroom.fcash.help.MyWebView web3;
	static com.nerdroom.fcash.help.MyWebView web0;
	static com.nerdroom.fcash.help.MyWebView web[]= new com.nerdroom.fcash.help.MyWebView [4];
	static public Copy_2_of_WorkActivity mn;
	private final int DELTA=10;
	static public ArrayList<articles> r;
	private static int index=0,index_prev,index_next;
	private static int index_p=0;
	public static boolean anima_start=false;
	public static Account ac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work);
        /* ... */
	    Bundle extras = getIntent().getExtras();
	    String url= extras.getString("url"); 
	 index=0;
		 index_p=0;
mn=this;

new AuthLouder(mn).execute();
        // Gesture detection
        gestureDetector = new GestureDetector(this, new MyGestureDetector());
        gestureListener = new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
            	
                return gestureDetector.onTouchEvent(event);
            }
        };
        
 //       bunner_ok();
        set_web();
        set_animation();
        web[0].setGone();
        web[2].setGone();
        web[3].setGone();
       
        //web1.setVisible();
        int i=0;
		//set_swipe(web2);
        while(i<web.length)
        {
       web[i].web.setOnClickListener(Copy_2_of_WorkActivity.this); 
        web[i].web.setOnTouchListener(gestureListener);
        web[i].setOnTouchListener(Copy_2_of_WorkActivity.this);
        i++;
        }
    
        
        WorkLouder wl=new WorkLouder(this,url,0);
	   wl.execute();
	   new Handler().postDelayed(new Runnable(){
           @Override
           public void run() {
  time_out=true;    	
           }
       }, 20000);
    }
public static void set_data()
{
	if(r!=null)
	{
	
	if(r.size()!=0)
	{
	int size=r.size()-1;
		web[1].loadUrl(r.get(size));	
		size--;
	if(size>0)
		{
		web[2].loadUrl(r.get(size));
		size--;
	//	index_next=2;
		}
	if(size>0)
		{
		web[3].loadUrl(r.get(size));
		size--;
		//index_next=3; 
		}
	index_next=size;
	index_prev=r.size()-1;
	}
	}
	if(r==null)mn.finish();
	if(r==null)mn.finish();
	if(r.size()==0)mn.finish();
}
public void prev()
{if(index_prev<r.size()){index_prev++; index_next++;} }
public void next()
{if(index_next>0){index_prev--; index_next--;} if(f_step){f_step=false;index_prev++;} }
public static void set_swipe(WebView wb)
{
//	wb.setOnClickListener(mn); 
    wb.setOnTouchListener(gestureListener);	
}
public static void get_money()
{
	com.nerdroom.fcash.help.Account ac=new com.nerdroom.fcash.help.Account();
	 ac.restore(mn);
	 token=ac.token;
	 user_id=ac.user_id;
	 new GetMoneyLouder(mn,token,user_id).execute();
	 
}
public void set_animation()
{
	
	trans_on_l = AnimationUtils.loadAnimation(this, R.anim.trans_on_l); 
	 trans_off_l = AnimationUtils.loadAnimation(this, R.anim.trans_off_l); 
	 trans_on_r = AnimationUtils.loadAnimation(this, R.anim.trans_on_r); 
	 trans_off_r = AnimationUtils.loadAnimation(this, R.anim.trans_off_r);
	 trans_off_l.setAnimationListener(new AnimationListener() {
	        @Override
	        public void onAnimationEnd(Animation arg0) {
	        	web[1].setGone();
	        	com.nerdroom.fcash.help.MyWebView web_t;
	        	web_t=web[0];
	        	anima_start=false;
	        	web[0]=web[1];
	        	web[1]=web[2];
	        	web[2]=web[3];
	        	web[3]=web_t;
	        		//Log.i("index+p",String.valueOf(index_prev));
	        		Log.i("index-index",String.valueOf(index_prev-index_next));	
	        	index_p++;
	        	Log.i("index+p",String.valueOf(index_prev));
	        	Log.i("index+n",String.valueOf(index_next));
	        //	Log.i("index_p",String.valueOf(index_p));
	        	if(index_next>0)	        		
	        	{
	        	web[3].loadUrl(r.get(index_next));	
	        	next();
	        	}
	        	Log.i("index-index",String.valueOf(index_prev-index_next));
	  
	        }

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				anima_start=true;	
			}
	 });
	 trans_off_r.setAnimationListener(new AnimationListener() {
	        @Override
	        public void onAnimationEnd(Animation arg0) {
	        	web[1].setGone();
	        	anima_start=false;
	        	com.nerdroom.fcash.help.MyWebView web_t;
	        	web_t=web[3];
	        	web[3]=web[2];
	        	web[2]=web[1];
	        	web[1]=web[0];
	        	web[0]=web_t;
	        	
	        
	        	
	        	
	        	index_p--;
	        	Log.i("index-index",String.valueOf(index_prev-index_next));
	        	Log.i("index+p",String.valueOf(index_prev));
	        	Log.i("index+n",String.valueOf(index_next));
	        	if(index_prev<r.size()-1)
	        	
	        	{		
	        		//Log.i("index",String.valueOf(index));
	        	web[0].loadUrl(r.get(index_prev));
	        	
	        	} 
	        	prev();
	        	Log.i("index-index",String.valueOf(index_prev-index_next));
	        }

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
			anima_start=true;	
			}
	 });	
}
public static void turn_left()
{
	if(!anima_start)
	if(index_p<r.size()-1)
	{
	web[1].startAnimation(trans_off_l);
	web[2].startAnimation(trans_on_l);
	web[2].setVisible();
	}
	//set_swipe(web1);
}
 
public static void turn_right()
{
	if(!anima_start)
	if(index_p>=0)
	{
	web[1].startAnimation(trans_off_r);
	web[0].startAnimation(trans_on_r);
	web[0].setVisible();	
	}
	//set_swipe(web2);
}
private void set_web()
{
	  web1= (com.nerdroom.fcash.help.MyWebView) findViewById(R.id.web1); 
	  web2= (com.nerdroom.fcash.help.MyWebView) findViewById(R.id.web2); 
	  web3= (com.nerdroom.fcash.help.MyWebView) findViewById(R.id.web3); 
//	  web0= (com.nerdroom.fcash.help.MyWebView) findViewById(R.id.web4);  
	 web[0]=web0;
	 web[1]=web1;
	 web[2]=web2;
	 web[3]=web3;
	 
	 
	    
	   
}

    class MyGestureDetector extends SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        	if(time_out)
        		
        		{
        //			mAsker.refreshBanner();
        		time_out=false;
        		coin_count++;
        		new BannerClickLouder(mn,token,user_id).execute();
        		tv_coin=mn.wg.get_tv(R.id.tv_coin);
        		tv_coin.setText(Integer.toString(coin_count));
        		new Handler().postDelayed(new Runnable(){
        	           @Override
        	           public void run() {
        	               /* Create an Intent that will start the Menu-Activity. */
        	        //   	web.setVisibility(View.GONE);
        	       time_out=true;    	
        	           }
        	       }, 20000);	
        		}
        	try {
                if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                {
                    return false;}
               
                // right to left swipe
                if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    Copy_2_of_WorkActivity.turn_left();
                }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                	Copy_2_of_WorkActivity.turn_right();
                }
            } catch (Exception e) {
                // nothing
            }
            return false;
        }

    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.d("BartActivity", "onKeyDown fired");

	//	if ((keyCode == KeyEvent.KEYCODE_BACK) && mBannerView.canGoBack()) {
	//		mBannerView.goBack();
	//		return true;
	//	}

		return super.onKeyDown(keyCode, event);
	}  
	@Override
	protected void onResume() {
		Log.d("BartActivity", "onResume fired");

		super.onResume();

		// Uncomment the block bellow if you want to use Plus1 WapStart Conversion SDK
		/**
		try {
			new Plus1ConversionTracker(this)
				.setTrackId(Place your WapStart Plus1 conversion track id here)
				.setCallbackUrl("wsp1hc://ru.wapstart.plus1.bart")
				.run();
		} catch (Plus1ConversionTracker.TrackIdNotDefinedException e) {
			Log.e("BartActivity", "You must define conversion track id");
		} catch (Plus1ConversionTracker.CallbackUrlNotDefinedException e) {
			Log.e("BartActivity", "You must define callback url");
		}
		*/

			//	mAsker.onResume();
	}

	@Override
	protected void onPause() {
		Log.d("BartActivity", "onPause fired");

		super.onPause();

	

	//	mAsker.onPause();
	}
	private void	bunner_ok()
	{
		mBannerView =
				(Plus1BannerView) findViewById(R.id.plus1BannerView);

			mAsker =
				new Plus1BannerAsker(
					new Plus1BannerRequest()
						.setApplicationId(10430/* Place your WapStart Plus1 application id here */),
					mBannerView
						.enableAnimationFromBottom()
						
				)
				; // default value

			mBannerView
				.addListener(new Plus1BannerView.OnShowListener() {
					public void onShow(Plus1BannerView pbv) {
						Log.d("BartActivity", "OnShowListener was touched");
					}
				})
				.addListener(new Plus1BannerView.OnHideListener() {
					public void onHide(Plus1BannerView pbv) {
						Log.d("BartActivity", "OnHideListener was touched");
					}
				})
				.addListener(new Plus1BannerView.OnCloseButtonListener() {
					public void onCloseButton(Plus1BannerView pbv) {
						Log.d("BartActivity", "OnCloseButtonListener was touched");
					}
				})
				.addListener(new Plus1BannerView.OnImpressionListener() {
					public void onImpression(Plus1BannerView pbv) {
						Log.d("BartActivity", "OnImpressionListener was touched");
					}
				})
				.addListener(new Plus1BannerView.OnTrackClickListener() {
					public void onTrackClick(Plus1BannerView pbv) {
						Log.d("BartActivity", "OnTrackClickListener was touched");
					}
				});	
		
	}	
	
	  private static void startLoginActivity() {
	        Intent intent = new Intent();
	        intent.setClass(mn, VKActivity.class);
	        mn.startActivityForResult(intent, REQUEST_LOGIN);
	    }
	    private void logOut() {
	        api=null;
	        account.access_token=null;
	        account.user_id=0;
	        account.save(Copy_2_of_WorkActivity.this);
	        
	    }
	    @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        if (requestCode == REQUEST_LOGIN) {
	            if (resultCode == RESULT_OK) {
	                //авторизовались успешно 
	                account.access_token=data.getStringExtra("token");
	                account.user_id=data.getLongExtra("user_id", 0);
	                account.save(Copy_2_of_WorkActivity.this);
	                api=new Api(account.access_token, Constants.API_ID);
	                
	            }
	        }
	        
	     
	    }
	    
	   static public void creat_api()
	   {
		   account.restore(mn);
		      if(account.access_token!=null)
              {
   		  
   		   api=new Api(account.access_token, Constants.API_ID);
   		
   		 
   		  
              }
   	   else
   	   		{
   		   startLoginActivity();   
   	   		}
	   }
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
	//if(event.getAction()==MotionEvent.ACTION_DOWN) 
	//	me=event;
	if(event.getAction()==MotionEvent.ACTION_UP)
	
	{
		if(me!=null)
		if((Math.abs(event.getX()-me.getX())<DELTA1) && (Math.abs(event.getY()-me.getY())<DELTA1))
		{
			//web[1].visible_menu(); me=null;
			
		}
		}

		return false;
	}
	
}