package com.nerdroom.funy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.nerdroom.fcash.adapter.MenuAdapter;
import com.nerdroom.fcash.adapter.SideMenuAdapter;
import com.nerdroom.fcash.help.MyActivity;
import com.nerdroom.fcash.help.base_url;
import com.nerdroom.fcash.model.MyMenuItem;
import com.nerdroom.fcash.model.RegData;
import com.nerdroom.fcash.model.SideMenuItem;
import com.nerdroom.fcash.vk.Account;
import com.nerdroom.fcash.vk.Constants;
import com.nerdroom.fcash.vk.VKActivity;
import com.nerdroom.fcash.vk.VkFriendsActivity;

import com.nerdroom.funy.R.color;
import com.nerdroom.json.DateLouder;
import com.nerdroom.json.LoginLouder;
import com.nerdroom.json.MenuLouder;
import com.nerdroom.json.StartLouder;
import com.nerdroom.json.VersionLouder;
import com.nerdroom.json.json_start;

import com.perm.kate.api.Api;
import com.perm.kate.api.FriendsList;
import com.perm.kate.api.User;




public class MenuActivity extends MyActivity {
	Dialog dialog=null;
	Button btn_menu;
	Context ctx;
	SlidingMenu menu;
	boolean Login=false, Login_menu=false;
	TextView tv_error;
	MyActivity mn;
	com.nerdroom.fcash.help.Account ac;
	SideMenuAdapter adapter;
	private boolean LOGIN=false;
	private boolean MENU=false;
	private boolean OPEN_MENU=false;
	Account account=new Account();
    Api api;
    private final int REQUEST_LOGIN=1;
    
	protected void onCreate(Bundle savedInstanceState) {
		//    setTheme(R.style.Theme_Sherlock);
		    super.onCreate(savedInstanceState);
		
		    setContentView(R.layout.activity_main);
		    ;    
		ctx=this;	    
	mn=this;
	get_data();
	if(ac.token!=null)
		if(ac.user_id!=null)
	new StartLouder(this).execute();		
	new DateLouder(this,ac.token,ac.user_id).execute();
	new VersionLouder(this,ac.token,ac.user_id).execute();
	slide_menu();
	set_menu();
	if(ac.firts_time==false)
		{
		pop_start();
		}
	
	tv_error=(TextView)findViewById(R.id.tv_error);
	tv_error.setOnClickListener(new View.OnClickListener() 
    {
    	public void onClick(View v) 
    	{
    		MenuLouder l=new MenuLouder(mn);
    		l.execute(json_start.api_path+base_url.cat+"0");
     } });	
	
	}
	public void set_menu()
	{
		MenuLouder l=new MenuLouder(mn);
		l.execute(json_start.api_path+base_url.cat+"0");
		
		
		btn_menu=wg.get_b(R.id.btn_menu);
		btn_menu.setOnClickListener(new View.OnClickListener() 
	    {
	    	public void onClick(View v) 
	    	{
	    menu.toggle();
	    		
	     } });	
	
		
	}
	private void get_data()
	{
		ac=new com.nerdroom.fcash.help.Account();
		ac.restore(ctx);
		if(ac.user_id!=null) Login=true;
	}
	
	    
	    
	   
	    
	    public void slide_menu()
	    {
	    	Display display = getWindowManager().getDefaultDisplay();
			Point size = new Point();
			display.getSize(size);
			int width = size.x;
			//int height = size.y;	
	    	int w=(width*6)/10;
	    	menu = new SlidingMenu(this);
	         menu.setMode(SlidingMenu.LEFT);
	         menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
	         menu.setFadeDegree(0.7f);
	         menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
	         menu.setMenu(R.layout.slidemenu);
	         menu.setBehindWidth(w);
	         menu.setBackgroundColor(color.simple_grey);
	        
	      ///   getActionBar().setHomeButtonEnabled(true);
	        ArrayList<SideMenuItem> menu_list=new ArrayList<SideMenuItem>();
	         
       menu_list.add(new SideMenuItem("Правила",SideMenuItem.HELP,BitmapFactory.decodeResource(getResources(), R.drawable.icon_help)));
       menu_list.add(new SideMenuItem("Лидеры",SideMenuItem.TOP,BitmapFactory.decodeResource(getResources(), R.drawable.icon_cubok)));  
       menu_list.add(new SideMenuItem("Пригласить",SideMenuItem.INVITE,BitmapFactory.decodeResource(getResources(), R.drawable.icon_add_us)));
   if(ac.user_id==null)
   {
	LOGIN=false;
	   menu_list.add(new SideMenuItem("Войти",SideMenuItem.LOGIN,BitmapFactory.decodeResource(getResources(), R.drawable.ikon_enter)));
       menu_list.add(new SideMenuItem("Регистрация",SideMenuItem.REG,BitmapFactory.decodeResource(getResources(), R.drawable.icon_register)));
   }   
   else 
   {
	   LOGIN=true;
	   menu_list.add(new SideMenuItem("Выйти",SideMenuItem.LOGOUT,BitmapFactory.decodeResource(getResources(), R.drawable.ikon_exit)));
       menu_list.add(new SideMenuItem("Личный кабинет",SideMenuItem.LK,BitmapFactory.decodeResource(getResources(), R.drawable.icon_user)));   
   }
       ListView list_m = (ListView) findViewById(R.id.side_menu_list);
	         adapter = new SideMenuAdapter(mn, menu_list); 
			list_m.setAdapter(adapter);
		 
	    MENU=true;     
	         //  MainActivity.menu = menu;
	      //   this.setMenuItems();  
	//fc_503909         
	         
	    }

	    
	    
	   ///вконтакте ---------------- 
	   
	    private void startLoginActivity() {
	        Intent intent = new Intent();
	        intent.setClass(this, VKActivity.class);
	        startActivityForResult(intent, REQUEST_LOGIN);
	    }
	    private void logOut() {
	        api=null;
	        account.access_token=null;
	        account.user_id=0;
	        account.save(MenuActivity.this);
	        
	    }
	    @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        if (requestCode == REQUEST_LOGIN) {
	            if (resultCode == RESULT_OK) {
	                //авторизовались успешно 
	                account.access_token=data.getStringExtra("token");
	                account.user_id=data.getLongExtra("user_id", 0);
	                account.save(MenuActivity.this);
	                api=new Api(account.access_token, Constants.API_ID);
	                
	            }
	        }
	    }
 protected void onResume()
{
super.onResume();
System.gc();
get_data();
set_menu();
new DateLouder(this,ac.token,ac.user_id).execute();
//if(MENU)
if(ac.user_id==null && LOGIN) 
	slide_menu();
if(ac.user_id!=null && !LOGIN) 
	slide_menu();
//

set_menu();
}
 protected void onPause()
 {
	 if(menu.isMenuShowing())
			menu.toggle(); 
	 super.onPause();
	 
 }
 public boolean onKeyDown(int keyCode, KeyEvent event)
 {
  if ((keyCode == KeyEvent.KEYCODE_BACK) && menu.isMenuShowing()) {
	  menu.toggle();
   return true;
  }
  return super.onKeyDown(keyCode, event);
 }	
 
public void pop_up_help()
{
	String title="Зарабатывай с улыбкой";
	String shure="Fcash приветствует вас. Fcash не только поднимет вам настроение, но и даст возможность заработать от 7500 до 750 руб. в месяц."
+"Подробности в Правилах. Хотите просмотреть правила прямо сейчас?";

	
	
	AlertDialog alert = new AlertDialog.Builder(mn)
			.create();
	alert.setTitle(title);

	alert.setMessage(shure);

	alert.setButton(mn.getString(R.string.no), new DialogInterface.OnClickListener() {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			dialog.dismiss();
		}
	});


		alert.setButton2(mn.getString(R.string.yes), new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				 Intent intent = new Intent();
			        intent.setClass(mn, HelpActivity.class);
			        mn.startActivity(intent);
				
			}
		});
	alert.show();
   	
}


private void pop_start()
{
	// custom dialog
				dialog = new Dialog(mn);
				dialog.setContentView(R.layout.pop_up);
				dialog.setTitle(mn.getString(R.string.pop_help_title));
	 
				// set the custom dialog components - text, image and button
			
				
	 
				Button yes = (Button) dialog.findViewById(R.id.yes);
				// if button is clicked, close the custom dialog
				yes.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent();
				        intent.setClass(mn, HelpActivity.class);
				        mn.startActivity(intent);
				        checked(dialog);
				        dialog.dismiss();
					}	
					});
				
				Button no = (Button) dialog.findViewById(R.id.no);
				// if button is clicked, close the custom dialog
				no.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						checked(dialog);
						dialog.dismiss();
					}
				});
	 
				dialog.show();	
}
public void checked(Dialog ctx)
		{
	CheckBox ch = (CheckBox) ctx.findViewById(R.id.check);
	if(ch.isChecked()) ac.set_begin(mn);
	
	
		}
}
