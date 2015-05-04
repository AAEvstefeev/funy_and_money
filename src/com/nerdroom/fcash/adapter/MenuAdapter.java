package com.nerdroom.fcash.adapter;



import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.nerdroom.fcash.model.MyMenuItem;
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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuAdapter extends BaseAdapter {
  Context ctx;
  LayoutInflater lInflater;
  ArrayList<MyMenuItem> objects;
  private int btn_count=0;
  private ArrayList<Integer> btn_bg;
  
  public MenuAdapter(Context context, ArrayList<MyMenuItem> arts) {
    ctx = context;
    objects = arts; 
    lInflater = (LayoutInflater) ctx
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    btn_bg = new ArrayList<Integer> ();
    btn_bg.add(R.drawable.key1_p);
    btn_bg.add(R.drawable.key2_p);
    btn_bg.add(R.drawable.key3_p);
    btn_bg.add(R.drawable.key4_p);
    //btn_bg.add(R.drawable.key5_p);
    
    
  }

  // ���-�� ���������

  public int getCount() {
	  if(objects!=null)
    return objects.size();
	  else
		  return 0;
		  
  }

  // ������� �� �������

  public Object getItem(int position) {
    return objects.get(position);
  }

  // id �� �������

  public long getItemId(int position) {
    return position;
  }

 
  @SuppressWarnings({ "unchecked", "deprecation" })
  public View getView(int position, View convertView, ViewGroup parent) {
    // ���������� ���������, �� �� ������������ view
    View view = convertView;
    if (view == null) {
   view = lInflater.inflate(R.layout.menu_item, parent, false);
    }

    final MyMenuItem p = getProduct(position);

    // ��������� View � ������ ������ ������� �� �������: ������������, ����
    // � ��������
    setViewTag(view, position );
  
  if(position==0) btn_count=0;
  
 
  //  ((TextView) view.findViewById(R.id.date_message)).setTypeface(Typeface.createFromAsset(ctx.getAssets(),"NewCenSchCyrBol.ttf"));
    Button title=(Button) view.findViewById(R.id.btn_menu_item);
    title.setText(p.title);
    title.setBackgroundResource(btn_bg.get(btn_count));
    btn_count++;
    if(btn_count>=btn_bg.size())btn_count=0;
//if(p.main) title.setBackgroundColor(R.color.android_green);
//else title.setBackgroundColor(R.color.honeycombish_blue);
 //   ((TextView) view.findViewById(R.id.what_say)).setTypeface(Typeface.createFromAsset(ctx.getAssets(),"NewCenSchCyrRom.ttf"));    
  
   title.setOnClickListener(new OnClickListener() {
	   MyMenuItem p1=p;
		  @Override
		  public void onClick(View arg0) {
			  Intent intent = new Intent(ctx, WorkActivity.class);
		       	 intent.putExtra("url", p.value);
		       	
		       	 ctx.startActivity(intent);
		    	  
			  }
	 
			});
	

	
//	 bitmap = codec(bitmap, Bitmap.CompressFormat.JPEG, 8);
	
 //p=null;
    return view;
  }

 
// ����� �� �������
  MyMenuItem getProduct(int position) {
    return ((MyMenuItem) getItem(position));
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
			MyMenuItem p = getProduct((Integer)v.getTag());

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

