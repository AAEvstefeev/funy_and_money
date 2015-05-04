package com.nerdroom.fcash.adapter;



import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.nerdroom.fcash.help.Account;
import com.nerdroom.fcash.model.TopUser;
import com.nerdroom.funy.WorkActivity;
import com.nerdroom.funy.R;



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
import android.util.Log;
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

public class TopAdapter extends BaseAdapter {
  Context ctx;
  LayoutInflater lInflater;
  ArrayList<TopUser> objects;
  int number_count=1; 
  boolean yes=true;
  String user_id;
  
  public TopAdapter(Context context, ArrayList<TopUser> arts) {
    ctx = context;
    objects = arts; 
    lInflater = (LayoutInflater) ctx
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
    Account ac=new Account();
    ac.restore(ctx);
  user_id=ac.user_id;  
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

  // пункт списка
  private Drawable resize(Drawable image) {
	    Bitmap d = ((BitmapDrawable)image).getBitmap();
	    Bitmap bitmapOrig = Bitmap.createScaledBitmap(d, 50, 50, false);
	    return new BitmapDrawable(bitmapOrig);
	}
  @SuppressWarnings({ "unchecked", "deprecation" })
  public View getView(int position, View convertView, ViewGroup parent) {
    // используем созданные, но не используемые view
    View view = convertView;
    if (view == null) {
   view = lInflater.inflate(R.layout.top_item, parent, false);
    }

    final TopUser p = getProduct(position);

    // заполнЯем View в пункте списка данными из товаров: наименование, цена
    // и картинка
    setViewTag(view, position );
  

  
 
  //  ((TextView) view.findViewById(R.id.date_message)).setTypeface(Typeface.createFromAsset(ctx.getAssets(),"NewCenSchCyrBol.ttf"));
    TextView name=(TextView) view.findViewById(R.id.tv_name);
  String st_name="";
    if(p.name.length()>20) st_name=p.name.substring(0,20)+"...";
    else st_name=p.name;
    st_name=st_name.replaceAll("\n", "");
    name.setText(st_name);
  
    TextView coin=(TextView) view.findViewById(R.id.tv_coin);
    coin.setText(Integer.toString(p.coin));
    TextView number=(TextView) view.findViewById(R.id.tv_number);
    
    number.setText(Integer.toString(position+1));
   
   if(false)
    if(user_id!=null)
 	   if(user_id.equals(p.id))
 		   
 	   {
 		 
 		   name.setTextColor(ctx.getResources().getColor(R.color.red));
 		   number.setTextColor(ctx.getResources().getColor(R.color.red));
 		   coin.setTextColor(ctx.getResources().getColor(R.color.red));
 		  Log.d("lider", p.id);
 	   }
    number_count++;
//if(p.main) title.setBackgroundColor(R.color.android_green);
//else title.setBackgroundColor(R.color.honeycombish_blue);
 //   ((TextView) view.findViewById(R.id.what_say)).setTypeface(Typeface.createFromAsset(ctx.getAssets(),"NewCenSchCyrRom.ttf"));    
  

	

	
//	 bitmap = codec(bitmap, Bitmap.CompressFormat.JPEG, 8);
	
 //p=null;
    return view;
  }

 
// товар по позиции
  TopUser getProduct(int position) {
    return ((TopUser) getItem(position));
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
			TopUser p = getProduct((Integer)v.getTag());

			ViewParent t = v.getParent();
				//BitmapDrawable image = new BitmapDrawable(BitmapFactory.decodeByteArray(p.bmpplus, 0, p.bmpplus.length));	
				
			
		   		  
		   	
		

			//	TextView textView = (TextView)getListView().getChildAt((Integer)v.getTag());	
		//TextView tx = (TextView) t.findViewById(R.id.kolvo);	
		//tx.setText("fdfd");
		} 
		private Bitmap codec(Bitmap src, Bitmap.CompressFormat format,int quality) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			src.compress(format, quality, os);
	 
			byte[] array = os.toByteArray();
			return BitmapFactory.decodeByteArray(array, 0, array.length);
		}
	  };
}

