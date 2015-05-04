package com.nerdroom.funy;






import java.io.File;
import java.util.ArrayList;

import ru.wapstart.plus1.sdk.Plus1BannerAsker;
import ru.wapstart.plus1.sdk.Plus1BannerRequest;
import ru.wapstart.plus1.sdk.Plus1BannerView;



import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.ProgressCallback;
import com.nerdroom.fcash.help.MyActivity;
import com.nerdroom.fcash.help.MyWebView;
import com.nerdroom.fcash.help.SettingHelper;
import com.nerdroom.fcash.help.base_url;
import com.nerdroom.fcash.help.md5;
import com.nerdroom.fcash.model.Response;
import com.nerdroom.fcash.model.articles;
import com.nerdroom.fcash.model.req_like;
import com.nerdroom.fcash.vk.Account;
import com.nerdroom.fcash.vk.Constants;
import com.nerdroom.fcash.vk.VKActivity;
import com.nerdroom.fcash.vk.VkFriendsActivity;
import com.nerdroom.json.AuthLouder;
import com.nerdroom.json.BannerClickLouder;
import com.nerdroom.json.GetMoneyLouder;
import com.nerdroom.json.MakeLike;
import com.nerdroom.json.SaveImage;
import com.nerdroom.json.WorkLouder;
import com.nerdroom.json.json_start;
import com.nerdroom.json.louder;
import com.perm.kate.api.Api;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.webkit.WebView;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;


public class WorkActivity extends MyActivity implements OnClickListener,OnTouchListener
{
	  public static int screenHeight;
	  public static   int screenWidth;
	public static boolean downlouding;
	static public	Account account=new Account();
  static public  Api api;
  static boolean portrait=true;
  static  private final int REQUEST_LOGIN=1;
	public static Plus1BannerAsker mAsker;
	private static Plus1BannerView mBannerView;
		 static TextView tv_coin;
		 public static String token;
		 public static String user_id;
		 public static int coin_count=0;
		 static boolean time_out=false;
		 static boolean save=false;
		 static int last_art=-3;
		 static boolean vis_menu=true;
		
		 public static LinearLayout sub_menu;
		public LinearLayout web_layout;
			public static Button btn_like;
			public Button btn_download;
			public Button btn_repost;
			public Button btn_new;
			public static TextView tv_like;	 
    public static final int SWIPE_MIN_DISTANCE = 120;
    public static final int SWIPE_MAX_OFF_PATH = 250;
    public static final int SWIPE_THRESHOLD_VELOCITY = 200;
    public static final int DELTA1=20;
    static Animation  trans_on_l;
	static Animation trans_off_l;
	static Animation trans_on_r;
	static Animation trans_off_r;
	static Animation fade_out,fade_in;
    private GestureDetector gestureDetector;
    static View.OnTouchListener gestureListener;
    MotionEvent me;
 
	static com.nerdroom.fcash.help.MyWebView web[]= new com.nerdroom.fcash.help.MyWebView [3];
	static public WorkActivity mn;
	private final int DELTA=10;
	static public articles[] r;
	private static int index=0;

	public static boolean anima_start=false;
	public static Account ac;
	static String url;
	
	 private static boolean ignor_memory=false;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work);
        downlouding=false;
        r=null;
        last_art=-3;
        save=false;
        portrait=true;
        index=0;
        get_screen();
        int or = getResources().getConfiguration().orientation;
        if((or==Surface.ROTATION_90)||(or==Surface.ROTATION_270)) portrait=false;
        coin_count=0;
        time_out=false;
	    Bundle extras = getIntent().getExtras();
	   url= extras.getString("url"); 
	    mn=this;
	   
		sub_menu = (LinearLayout)findViewById(R.id.sub_menu);
		btn_like = (Button)findViewById(R.id.like);
		btn_repost = (Button)findViewById(R.id.repost);
		btn_download = (Button)findViewById(R.id.download);
		btn_new = (Button)findViewById(R.id.btn_new);
		tv_like=(TextView) findViewById(R.id.like_number);
		
get_money();	
	  
init();

        gestureDetector = new GestureDetector(this, new MyGestureDetector());
        gestureListener = new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
            	
                return gestureDetector.onTouchEvent(event);
            }
        };
        
        
        set_web();
        set_animation();
       
       
       
        int i=0;
        while(i<web.length)
        {
    web[i].web.setOnClickListener(WorkActivity.this); 
        i++;
        }
    
        
      
        
       WorkLouder wl=new WorkLouder(this,url,sh.get_art_number(url));
	   wl.execute();
	   
	   //начисление монет
	   
	   	new Handler().postDelayed(new Runnable(){
           @Override
           public void run() {
  		time_out=true;    	
           }
       	}, 20000);
	      
	 //  bunner_ok();    
    }
/**    
public static void set_data()
{
	sh=new SettingHelper(mn);
	last_art=sh.get_art_number(url);
	if(r!=null)
	{
	
	if(r.size()!=0)
	{
	int size;
	
	if(last_art==-2)
	{
		size=0;//r.size();
	}
	else 
	{
		size=last_art+1;
	}
	
	if(last_art==-2)
	{
		if(size<r.size()) web[0].loadUrl(r.get(size));	
		
		web[1].loadUrl(r.get(size+1));	
		size++;
	if(size>0)
		{
		web[0].loadUrl(r.get(size+1));
		size++;
		
		}	
	index=size-1;
	}
	else
	{
	if(size<r.size()) web[0].loadUrl(r.get(size));	
	
		web[1].loadUrl(r.get(size-1));	
		size--;
	if(size>0)
		{
		web[2].loadUrl(r.get(size-1));
		size--;
		
		}
	index=size+1;
	}
	
	
	}
	}
	if(r==null)mn.finish();
	
	if(r.size()==0)mn.finish();

	   tv_like.setText(web[1].likes);
}
**/

public static void set_data()
{
	sh=new SettingHelper(mn);
	if(last_art==-3)
	last_art=sh.get_art_number(url);
	if(r!=null)
	{
	if(r.length==1)
	{
		web[1].loadUrl(r[0]);
		index=0;
		return;
	}
		
	if(r.length!=0)
	{
	int size;
	
	if(last_art==-2 ||ignor_memory)
	{
		size=r.length;
		ignor_memory=false;
	}	
	else 
		size=last_art+1;
	if(size<r.length) web[0].loadUrl(r[size]);	
	
		web[1].loadUrl(r[size-1]);	
		size--;
	if(size>0)
		{
		web[2].loadUrl(r[size-1]);
		size--;
		
		}
	index=size+1;
	
	}
	}
	if(r==null)mn.finish();
	
	if(r.length==0)mn.finish();

	   tv_like.setText(web[1].likes);
	   
	   if(r!=null)
		   if(r.length!=0)
			   			save=true;
	   
	  
	  
	   new WorkLouder(mn,url,-1).execute();
	   new WorkLouder(mn,url,-2).execute();
	   bunner_ok();
	   if(mAsker!=null)
		   mAsker.onResume();
	  
}

public static void set_swipe(WebView wb)
{
//	wb.setOnClickListener(mn); 
  //  wb.setOnTouchListener(gestureListener);	
}
public static void get_money()
{
	com.nerdroom.fcash.help.Account ac=new com.nerdroom.fcash.help.Account();
	 ac.restore(mn);
	 token=ac.token;
	 user_id=ac.user_id;
	 GetMoneyLouder gl= new GetMoneyLouder(mn,token,user_id);
	 if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
 	    gl.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Void[])null);
 	else
 	    gl.execute((Void[])null);
	 
}
public void set_animation()
{
	
	trans_on_l = AnimationUtils.loadAnimation(this, R.anim.trans_on_l); 
	 trans_off_l = AnimationUtils.loadAnimation(this, R.anim.trans_off_l); 
	 trans_on_r = AnimationUtils.loadAnimation(this, R.anim.trans_on_r); 
	 trans_off_r = AnimationUtils.loadAnimation(this, R.anim.trans_off_r);
	 fade_in = AnimationUtils.loadAnimation(this, R.anim.fade_in);
	 fade_out = AnimationUtils.loadAnimation(this, R.anim.fade_out);
	 fade_out.setAnimationListener(new AnimationListener() {
	        @Override
	        public void onAnimationEnd(Animation arg0) {
	       sub_menu.setVisibility(View.GONE);
	     
	        }

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
	 });
	 trans_off_l.setAnimationListener(new AnimationListener() {
	        @Override
	        public void onAnimationEnd(Animation arg0) {
	        MyWebView web1 = web[0];
	        web[1].active=false;
	        web[1].setGone();
	        web[0]=web[1];
	        web[1]=web[2];
	        web[2]=web1;
	        index--;	        
	       if(index>0) web[2].loadUrl(r[index-1]);
	        anima_start=false;
	        web[1].setVisible();
	        web[1].active=true;
	        web[1].web.setVisibility(View.VISIBLE);
	        tv_like.setText(web[1].likes);
	        Log.i("index",String.valueOf(index));
	        web[0].web.reset();
	        web[2].web.reset();
	        sh.save_art_number(url, index);
	        }

			@Override
			public void onAnimationRepeat(Animation animation) 
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onAnimationStart(Animation animation) 
			{
				// TODO Auto-generated method stub
				anima_start=true;	
			}
	 });
	 trans_off_r.setAnimationListener(new AnimationListener() 
	 {
	        @Override
	        public void onAnimationEnd(Animation arg0) {
	        MyWebView web1 = web[2];
	        web[1].active=false;
	        web[1].setGone();
	        web[2]=web[1];
	        web[1]=web[0];
	        web[0]=web1;
	        index++;
	        if(index<r.length-1)
	        web[0].loadUrl(r[index+1]);
	        web[1].setVisible();
	        anima_start=false;
	        web[1].web.setVisibility(View.VISIBLE);
	        web[1].active=true;
	       tv_like.setText(web[1].likes);
	        Log.i("index",String.valueOf(index));
	        web[0].web.reset();
	        web[2].web.reset();
	        sh.save_art_number(url, index);
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
	if(!portrait)
	if(!anima_start)
	if(index>0)
	{
	
	web[1].startAnimation(trans_off_l);
	web[2].setVisible();
	web[2].startAnimation(trans_on_l);
	plus_coin();
	}
	
	
}
 
public static void turn_right()
{
	if(!portrait)
	if(!anima_start)
	if(index<r.length-1)
	{
	web[1].startAnimation(trans_off_r);
	web[0].setVisible();
	web[0].startAnimation(trans_on_r);
	plus_coin();	
	}
	
	
}
private void set_web()
{
	web[1]= (com.nerdroom.fcash.help.MyWebView) findViewById(R.id.web1); 
	web[2]= (com.nerdroom.fcash.help.MyWebView) findViewById(R.id.web2); 
	web[0]= (com.nerdroom.fcash.help.MyWebView) findViewById(R.id.web3);  
	web[0].setGone();
	web[2].setGone();
	 
	 
	    
	   
}
public static void plus_coin()
{
	if(time_out)
		
	{
			
			
	time_out=false;
	
	BannerClickLouder bc= new BannerClickLouder(mn,token,user_id);
	 if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
	 	    bc.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Void[])null);
	 	else
	 	    bc.execute((Void[])null);
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
}

    class MyGestureDetector extends SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        
        	try {
                if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                {
                    return false;}
               
                // right to left swipe
                if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    WorkActivity.turn_left();
                }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                	WorkActivity.turn_right();
                }
            } catch (Exception e) {
                // nothing
            }
            return false;
        }

    }
    int it = 0;
   
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		 it++ ;
         Handler handler = new Handler();
         Runnable r = new Runnable() {

             @Override
             public void run() {

                 it = 0;
             }
         };
         if(it==1){
             handler.postDelayed(r, 250);
         }else if(it == 2){
          it = 0;
      //  visible_menu();
      }
	
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.d("BartActivity", "onKeyDown fired");
		if(mBannerView!=null)
		if ((keyCode == KeyEvent.KEYCODE_BACK) && mBannerView.canGoBack()) {
			mBannerView.goBack();
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}  
	@Override
	protected void onResume() {
		Log.d("WorkActivity", "onResume fired");

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
if(mAsker!=null)
				mAsker.onResume();
	}

	@Override
	protected void onPause() {
		Log.d("BartActivity", "onPause fired");

		super.onPause();
		if(mAsker!=null)
		mAsker.onPause();
	

	
	}
	private static void	bunner_ok()
	{
		mBannerView =
				(Plus1BannerView) mn.findViewById(R.id.plus1BannerView);
		
		mAsker =
				new Plus1BannerAsker(
					new Plus1BannerRequest()
						.setApplicationId(11128),
					mBannerView
						.enableAnimationFromTop()
						
				)
				.setRefreshDelay(0); // default value

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
	        account.save(WorkActivity.this);
	        
	    }
	    @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        if (requestCode == REQUEST_LOGIN) {
	            if (resultCode == RESULT_OK) {
	                //авторизовались успешно 
	                account.access_token=data.getStringExtra("token");
	                account.user_id=data.getLongExtra("user_id", 0);
	                account.save(WorkActivity.this);
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
	//	if(me!=null)
	//	if((Math.abs(event.getX()-me.getX())<DELTA1) && (Math.abs(event.getY()-me.getY())<DELTA1))
//		{visible_menu(); me=null;}
		}

		return false;
	}
	public  static  void visible_menu() {
		// TODO Auto-generated method stub
	//	LayoutParams params = new LayoutParams(
		//        LayoutParams.WRAP_CONTENT,      
		//        LayoutParams.WRAP_CONTENT);
		
		
///web[1].web.reset();	
	    	if(sub_menu.getVisibility()==View.VISIBLE) 
	    	{
	    		sub_menu.startAnimation(fade_out);
	    	//	params.setMargins(0, 0, 0, 0);
	    	}
	    	else
	    	{
	    		sub_menu.startAnimation(fade_in);
	    		sub_menu.setVisibility(View.VISIBLE);
	    	//	params.setMargins(0, 140, 0, 140);
	    	}
	    	//for(int i=0;i<web.length;i++)
	    	//web[i].setLayoutParams(params);	
	    //	vis_menu=false;
	    		
	}	
	public void save()
	{
		btn_download.setOnClickListener(new View.OnClickListener() 
	    {
	    	public void onClick(View v) 
	    	{
	    		int i=0;
	    		if(downlouding==false)
	    		while(i<web[1].image_mass.size()){
	    		downlouding=true;
	    		SaveImage sv = new SaveImage(web[1].image_mass.get(i),web[1]);
	    		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
		    	    sv.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Void[])null);
		    	else
		    	    sv.execute((Void[])null);
	    			
	    			
	    		i++;
	    	}
	     } });		
	}

	public boolean repost()
	{
		btn_repost.setOnClickListener(new View.OnClickListener() 
	    {
	    	public void onClick(View v) 
	    	{
	    		mAsker.setDisabledWebViewCorePausing(true);
	    		postToWall();
	    		Toast.makeText(WorkActivity.mn, "Запись успешно добавлена", Toast.LENGTH_LONG).show();
	     } });	

		return false;	
	}
	public boolean like()
	{
		btn_like.setOnClickListener(new View.OnClickListener() 
	    {
	    	public void onClick(View v) 
	    	{
	    		
	    	      
	    	      
	    		com.nerdroom.fcash.help.Account ac = new com.nerdroom.fcash.help.Account();
	    		 ac.restore(mn);
	    	//	 if(ac.user_id!=null)
	    		// JsonObject json = new JsonObject();
	    		// json.addProperty("foo", "bar");
	    		   TelephonyManager telephonyManager = (TelephonyManager)WorkActivity.mn.getSystemService(Context.TELEPHONY_SERVICE);
	    		    json_start json = new json_start(mn);	
	    		    String id = telephonyManager.getDeviceId();
	    		    id=md5.md5(id);
	    			String json_st=null; 
	    			json_st= json.gson.toJson(new req_like(id,web[1].art_id));
	    			String path=json.api_path+"article_do_like/";
	    			Response rs=null;
	    
	    			
	    	MakeLike mk =  new MakeLike(ac.user_id,web[1].art_id,web[1]);
	    	
	    	
	    	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
	    	    mk.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Void[])null);
	    	else
	    	    mk.execute((Void[])null);
	    	
	    		// else 
	    		//	 btn_like.setClickable(false);		 
	     } });
		return false;	
	}
	public void postToWall() {
	    //Общение с сервером в отдельном потоке чтобы не блокировать UI поток
	    new Thread(){
	        @Override
	        public void run(){
	            try {
	               
	            	if(WorkActivity.api==null || WorkActivity.account==null)
	            		WorkActivity.creat_api();      		
	            		
	            		String smap_text=web[1].url;
	            		
	            		ArrayList<String> t=new ArrayList<String>();
	            		t.add(web[1].url);
						WorkActivity.api.createWallPost(WorkActivity.account.user_id, smap_text, web[1].image_mass, null, false, false, false, null, null, null, null, null);
						//Toast.makeText(WorkActivity.mn, "Запись успешно добавлена", Toast.LENGTH_LONG).show(); 

	                //Показать сообщение в UI потоке 
	             //   WorkActivity.runOnUiThread(successRunnable);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }

			
	    }.start();
	}
	Runnable successRunnable=new Runnable(){
	    @Override
	    public void run() {
	    	Toast.makeText(WorkActivity.mn, "Запись успешно добавлена", Toast.LENGTH_LONG).show();   
	        
		    
	    }
	};
public void init()
{
	like();
	save();
	repost();
	to_new();
}
private void to_new() {
	// TODO Auto-generated method stub
	btn_new.setOnClickListener(new View.OnClickListener() 
    {
    	public void onClick(View v) 
    	{
    	ignor_memory=true;	
    	last_art=index;
    	set_data();	
    	btn_new.setText(getString(R.string.back));
    	back();
    	}
    	});
}
private void back() {
	// TODO Auto-generated method stub
	btn_new.setOnClickListener(new View.OnClickListener() 
    {
    	public void onClick(View v) 
    	{
    	
    	set_data();	
    	btn_new.setText(getString(R.string.to_new));
    	to_new();
    	}
    	});
}

public void get_screen()
{
	 //размер дисплея
    DisplayMetrics displaymetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
    screenHeight = displaymetrics.heightPixels;
    screenWidth = displaymetrics.widthPixels;	
}
public void onDestroy()
{
//if(save)	
//	sh.save_art_number(url, index);
	super.onDestroy();	
}
}