package com.nerdroom.fcash.adapter;



import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import com.nerdroom.fcash.help.Account;
import com.nerdroom.fcash.help.MyActivity;
import com.nerdroom.fcash.vk.VKActivity;
import com.nerdroom.fcash.vk.VkFriendsActivity;
import com.nerdroom.funy.HelpActivity;
import com.nerdroom.funy.InviteActivity;
import com.nerdroom.funy.LkActivity;
import com.nerdroom.funy.LoginActivity;
import com.nerdroom.funy.MenuActivity;
import com.nerdroom.funy.WorkActivity;
import com.nerdroom.funy.R;
import com.nerdroom.funy.RegActivity;
import com.nerdroom.funy.TopActivity;
import com.nerdroom.fcash.model.SideMenuItem;



import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.Messenger;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

public class SideMenuAdapter extends BaseAdapter {
	MyActivity ctx;
  LayoutInflater lInflater;
  ArrayList<SideMenuItem> objects;
  private int btn_count=0;

  
  public SideMenuAdapter(MyActivity mn, ArrayList<SideMenuItem> arts) {
    ctx = mn;
    objects = arts; 
    lInflater = (LayoutInflater) ctx
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    
    
  }

  // кол-во элементов

  public int getCount() {
	  if(objects!=null)
    return objects.size();
	  else
		  return 0;
		  
  }

  // элемент по позиции

  public Object getItem(int position) {
    return objects.get(position);
  }

  // id по позиции

  public long getItemId(int position) {
    return position;
  }

 
  @SuppressWarnings({ "unchecked", "deprecation" })
  public View getView(int position, View convertView, ViewGroup parent) {
    // используем созданные, но не используемые view
    View view = convertView;
    if (view == null) {
   view = lInflater.inflate(R.layout.side_menu_item, parent, false);
    }

    final SideMenuItem p = getProduct(position);

    // заполнЯем View в пункте списка данными из товаров: наименование, цена
    // и картинка
    setViewTag(view, position );
    Bitmap bmp=null;
    ImageView pic=null; 
     pic=(ImageView) view.findViewById(R.id.pic);
    Button title=(Button) view.findViewById(R.id.title);    
    title.setText(p.title);
    pic.setImageBitmap(p.pic);
    
   
//if(p.main) title.setBackgroundColor(R.color.android_green);
//else title.setBackgroundColor(R.color.honeycombish_blue);
 //   ((TextView) view.findViewById(R.id.what_say)).setTypeface(Typeface.createFromAsset(ctx.getAssets(),"NewCenSchCyrRom.ttf"));    
  
    title.setOnClickListener(new OnClickListener() {
	   SideMenuItem p1=p;
		  @Override
		  public void onClick(View arg0) {
			  Intent myIntent = new Intent();
			  switch(p.open){
			  case SideMenuItem.LOGIN:
			  myIntent.setClass(ctx,LoginActivity.class);
			  break;
			  case SideMenuItem.LOGOUT:
			{    	
				Account ac=new Account();
				ac.clear(ctx);
				ctx.finish();
				myIntent.setClass(ctx, MenuActivity.class);	
			}
			  break;
			  case SideMenuItem.REG:
			  myIntent.setClass(ctx, RegActivity.class);
			  break;
			  case SideMenuItem.INVITE:
			  myIntent.setClass(ctx, InviteActivity.class);
			  break;
			  case SideMenuItem.TOP:
				  myIntent.setClass(ctx, TopActivity.class);
				  break;
			  case SideMenuItem.LK:
				  myIntent.setClass(ctx, LkActivity.class);
				  break;
			  case SideMenuItem.HELP:
				  myIntent.setClass(ctx, HelpActivity.class);
				  break;
			  case SideMenuItem.EXIT:
				ctx.finish();
				  break;
				  
				  
			  }
			  ctx.startActivity(myIntent); 
		    	  
			  }
	 
			});
	

	
//	 bitmap = codec(bitmap, Bitmap.CompressFormat.JPEG, 8);
	
 //p=null;
    return view;
  }


// товар по позиции
  SideMenuItem getProduct(int position) {
    return ((SideMenuItem) getItem(position));
  }


  private void setViewTag(View view, Object tag) {
	  
      view.setTag(tag);

      if (view instanceof ViewGroup) {

          for (int i=0; i < ((ViewGroup) view).getChildCount(); i++) {

              setViewTag(((ViewGroup) view).getChildAt(i), tag);

          }

      }

  }
  
  OnClickListener ImageClick = new OnClickListener() {
		public void onClick(View v) {
			// TODO Auto-generated method stub
			SideMenuItem p = getProduct((Integer)v.getTag());

			ViewParent t = v.getParent();
				//BitmapDrawable image = new BitmapDrawable(BitmapFactory.decodeByteArray(p.bmpplus, 0, p.bmpplus.length));	
				
			
		   		  
		   	
		

			//	TextView textView = (TextView)getListView().getChildAt((Integer)v.getTag());	
		//TextView tx = (TextView) t.findViewById(R.id.kolvo);	
		//tx.setText("fdfd");
		} 
		
	  };
}

