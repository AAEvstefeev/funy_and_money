package com.nerdroom.funy;






import java.util.ArrayList;

import ru.wapstart.plus1.sdk.Plus1BannerAsker;
import ru.wapstart.plus1.sdk.Plus1BannerRequest;
import ru.wapstart.plus1.sdk.Plus1BannerView;



import com.nerdroom.fcash.help.MyActivity;
import com.nerdroom.fcash.help.MyWebView;
import com.nerdroom.fcash.help.SettingHelper;
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
import com.nerdroom.json.MakeLike;
import com.nerdroom.json.SaveImage;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;


public class reservedWorkActivity extends MyActivity implements OnClickListener,OnTouchListener
{
	static public	Account account=new Account();
  static public  Api api;
  static  private final int REQUEST_LOGIN=1;
	private Plus1BannerAsker mAsker;
		private Plus1BannerView mBannerView;
		 static TextView tv_coin;
		 public static String token;
		 public static String user_id;
		 public static int coin_count=0;
		 static boolean time_out=false;
		
		 static int last_art=-1;
		 static boolean vis_menu=true;
		 public static LinearLayout sub_menu;
		public LinearLayout web_layout;
			public static Button btn_like;
			public Button btn_download;
			public Button btn_repost;
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
	static public reservedWorkActivity mn;
	private final int DELTA=10;
	static public ArrayList<articles> r;
	private static int index=0;
	private static int index_p=0;
	public static boolean anima_start=false;
	public static Account ac;
	static String url;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work);
        coin_count=0;
        time_out=false;
	    Bundle extras = getIntent().getExtras();
	   url= extras.getString("url"); 
	    mn=this;
	   
		sub_menu = (LinearLayout)findViewById(R.id.sub_menu);
	//	web_layout = (LinearLayout)findViewById(R.id.web_layout);
		btn_like = (Button)findViewById(R.id.like);
		btn_repost = (Button)findViewById(R.id.repost);
		btn_download = (Button)findViewById(R.id.download);
		tv_like=(TextView) findViewById(R.id.like_number);
	//	sub_menu.setVisibility(View.GONE);
	  
init();

	//	new AuthLouder(mn).execute();
        // Gesture detection
        gestureDetector = new GestureDetector(this, new MyGestureDetector());
        gestureListener = new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
            	
                return gestureDetector.onTouchEvent(event);
            }
        };
        
        bunner_ok();
        set_web();
        set_animation();
       
       
        //web1.setVisible();
        int i=0;
		//set_swipe(web2);
        while(i<web.length)
        {
    web[i].web.setOnClickListener(reservedWorkActivity.this); 
     //   web[i].web.setOnTouchListener(gestureListener);
 //       web[i].setOnTouchListener(WorkActivity.this);
        i++;
        }
    
        
     //   WorkLouder wl=new WorkLouder(this,url);
	//   wl.execute();
	   new Handler().postDelayed(new Runnable(){
           @Override
           public void run() {
  time_out=true;    	
           }
       }, 20000);
	   
	   
    }
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
		size=r.size();
	else 
		size=last_art+1;
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
	if(r==null)mn.finish();
	
	if(r.size()==0)mn.finish();

	   tv_like.setText(web[1].likes);
}
/*
public static void set_data()
{
	if(r!=null)
	{
	
	if(r.size()!=0)
	{
	int size=r.size();
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
	if(r==null)mn.finish();
	
	if(r.size()==0)mn.finish();

	   tv_like.setText(web[1].likes);
}
*/
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
	// new GetMoneyLouder(mn,token,user_id).execute();
	 
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
	       if(index>0) web[2].loadUrl(r.get(index-1));
	        anima_start=false;
	        web[1].setVisible();
	        web[1].active=true;
	        web[1].web.setVisibility(View.VISIBLE);
	        tv_like.setText(web[1].likes);
	        Log.i("index",String.valueOf(index));
	        web[0].web.reset();
	        web[2].web.reset();
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
	        MyWebView web1 = web[2];
	        web[1].active=false;
	        web[1].setGone();
	        web[2]=web[1];
	        web[1]=web[0];
	        web[0]=web1;
	        index++;
	        if(index<r.size()-1)
	        web[0].loadUrl(r.get(index+1));
	        web[1].setVisible();
	        anima_start=false;
	        web[1].web.setVisibility(View.VISIBLE);
	        web[1].active=true;
	       tv_like.setText(web[1].likes);
	        Log.i("index",String.valueOf(index));
	        web[0].web.reset();
	        web[2].web.reset();
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
	if(index>0)
	{
	
	web[1].startAnimation(trans_off_l);
	web[2].setVisible();
	web[2].startAnimation(trans_on_l);
	plus_coin();
	}
	
	//set_swipe(web1);
}
 
public static void turn_right()
{
	if(!anima_start)
	if(index<r.size()-1)
	{
	web[1].startAnimation(trans_off_r);
	web[0].setVisible();
	web[0].startAnimation(trans_on_r);
	plus_coin();	
	}
	
	//set_swipe(web2);
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
//			mAsker.refreshBanner();
	time_out=false;
	coin_count++;
	//new BannerClickLouder(mn,token,user_id).execute();
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
                    reservedWorkActivity.turn_left();
                }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                	reservedWorkActivity.turn_right();
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
	        account.save(reservedWorkActivity.this);
	        
	    }
	    @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        if (requestCode == REQUEST_LOGIN) {
	            if (resultCode == RESULT_OK) {
	                //авторизовались успешно 
	                account.access_token=data.getStringExtra("token");
	                account.user_id=data.getLongExtra("user_id", 0);
	                account.save(reservedWorkActivity.this);
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
	    		while(i<web[1].image_mass.size()){
	    		new SaveImage(web[1].image_mass.get(i),web[1]).execute();
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
	    		postToWall();
	    		Toast.makeText(reservedWorkActivity.mn, "Запись успешно добавлена", Toast.LENGTH_LONG).show();
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
	    			 
	    	new MakeLike(ac.user_id,web[1].art_id,web[1]).execute();
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
	               
	            	if(reservedWorkActivity.api==null || reservedWorkActivity.account==null)
	            		reservedWorkActivity.creat_api();      		
	            		
	            		String smap_text=web[1].url;
	            		
	            		ArrayList<String> t=new ArrayList<String>();
	            		t.add(web[1].url);
						reservedWorkActivity.api.createWallPost(reservedWorkActivity.account.user_id, smap_text, web[1].image_mass, null, false, false, false, null, null, null, null, null);
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
	    	Toast.makeText(reservedWorkActivity.mn, "Запись успешно добавлена", Toast.LENGTH_LONG).show();   
	        
		    
	    }
	};
public void init()
{
	like();
	save();
	repost();
	
}
public void onDestroy()
{
	sh.save_art_number(url, index);
	super.onDestroy();	
}
}