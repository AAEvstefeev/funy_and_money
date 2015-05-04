package com.nerdroom.fcash.adapter;



import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import com.nerdroom.fcash.model.categories;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebSettings.PluginState;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

public class WorkAdapter extends BaseAdapter {
  Context ctx;
  LayoutInflater lInflater;
  ArrayList<categories> objects;
  private int btn_count=0;
  private ArrayList<Integer> btn_bg;
private android.webkit.WebSettings WebSettings;
  
  public WorkAdapter(Context context, ArrayList<categories> arts) {
    ctx = context;
    objects = arts; 
    lInflater = (LayoutInflater) ctx
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    btn_bg = new ArrayList<Integer> ();
    btn_bg.add(R.drawable.key1);
    btn_bg.add(R.drawable.key2);
    btn_bg.add(R.drawable.key3);
    btn_bg.add(R.drawable.key4);
    btn_bg.add(R.drawable.key5);
    
    
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
  public synchronized	 View getView(int position, View convertView, ViewGroup parent) {
    // используем созданные, но не используемые view
    View view = convertView;
    if (view == null) {
   view = lInflater.inflate(R.layout.work_item, parent, false);
    }

    final categories p = getProduct(position);

    // заполнЯем View в пункте списка данными из товаров: наименование, цена
    // и картинка
    setViewTag(view, position );
  
  
  
 
  //  ((TextView) view.findViewById(R.id.date_message)).setTypeface(Typeface.createFromAsset(ctx.getAssets(),"NewCenSchCyrBol.ttf"));
    if(true)
    {
  	  WebView web=(WebView) view.findViewById(R.id.web_work);
  	 ProgressBar bar=(ProgressBar) view.findViewById(R.id.bar);
  	 bar.setVisibility(View.VISIBLE);
  	   web.setVisibility(View.VISIBLE);  
  	  web.getSettings().setDefaultTextEncodingName("UTF-8");
	 // web.getSettings().setJavaScriptEnabled(true);
	  web.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
	  web.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
	//  web.getSettings().setCacheMode(web.getSettings().LOAD_NO_CACHE);
	  android.webkit.WebSettings wb= web.getSettings();
	 wb.setRenderPriority(android.webkit.WebSettings.RenderPriority.HIGH) ;
	 Runnable r;
	// if(p.link!=null)
	 {	 
	 //    	r = new WorkThread(web,p.link);
	  //   	new Thread(r).start();
	  //   	web.loadUrl(p.link);
	    // 	p.link=null;
	 }

	 bar.setVisibility(View.GONE);
	// WorkThread.loud=false;
 	  
  	  
    }
    if(false)
  {
    Button title=(Button) view.findViewById(R.id.btn_menu_item);
    title.setVisibility(View.VISIBLE);
    title.setText(p.title);
    title.setBackgroundResource(btn_bg.get(btn_count));
    btn_count++;
    if(btn_count>=btn_bg.size())btn_count=0;
//if(p.main) title.setBackgroundColor(R.color.android_green);
//else title.setBackgroundColor(R.color.honeycombish_blue);
 //   ((TextView) view.findViewById(R.id.what_say)).setTypeface(Typeface.createFromAsset(ctx.getAssets(),"NewCenSchCyrRom.ttf"));    
  
   title.setOnClickListener(new OnClickListener() {
	   categories p1=p;
		  @Override
		  public void onClick(View arg0) {
			  Intent intent = new Intent(ctx, WorkActivity.class);
		       	 intent.putExtra("url", p.id);
		       	
		       	 ctx.startActivity(intent);
		    	  
			  }
	 
			});
	
  }



    return view;
  }

 
// товар по позиции
  categories getProduct(int position) {
    return ((categories) getItem(position));
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
			categories p = getProduct((Integer)v.getTag());

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

