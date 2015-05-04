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


import com.nerdroom.fcash.help.MyActivity;
import com.nerdroom.fcash.help.VK_User;
import com.nerdroom.fcash.vk.VKActivity;
import com.nerdroom.fcash.vk.VkFriendLouder;
import com.nerdroom.fcash.vk.VkFriendsActivity;
import com.nerdroom.funy.WorkActivity;
import com.nerdroom.funy.R;
import com.perm.kate.api.User;



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

public class VkAdapter extends BaseAdapter {
	VkFriendsActivity ctx;
  LayoutInflater lInflater;
  ArrayList<VK_User> objects;
  private int btn_count=0;
  Bitmap bmp=null;
 // ImageView foto=null;
  
  public VkAdapter(VkFriendsActivity mn, ArrayList<VK_User> arts) {
    ctx = mn;
    int i = 0;
    objects=arts;
   // while(i<arts.size()){
   // objects.add((VK_User) arts.get(i));  
   // i++;
   // }
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
   view = lInflater.inflate(R.layout.vk_item, parent, false);
    }

    final VK_User p = getProduct(position);

    // заполнЯем View в пункте списка данными из товаров: наименование, цена
    // и картинка
    setViewTag(view, position );
  
  
    // foto=(ImageView) view.findViewById(R.id.vk_foto);
    TextView title=(TextView) view.findViewById(R.id.vk_name);
 
    title.setText(p.user.first_name+" "+p.user.last_name);
  
    CheckBox check=(CheckBox) view.findViewById(R.id.check);
    check.setTag(Integer.valueOf(position));
	check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
		
		   @Override
		   public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
			   
			   objects.get((Integer)buttonView.getTag()).checked = isChecked;    
		   }
		});
	check.setChecked(p.checked);
	 /** 
//  foto.setVisibility(View.VISIBLE);

 if(p.photo!=null) foto.setImageBitmap(p.photo);
 else
 {
	 foto.setVisibility(View.GONE);
	// foto.setBackgroundDrawable(view.getResources().getDrawable(R.drawable.ic_launcher)); 
	new VkFriendLouder(foto,view).execute(p);
 }
**/
   
   
//if(p.main) title.setBackgroundColor(R.color.android_green);
//else title.setBackgroundColor(R.color.honeycombish_blue);
 //   ((TextView) view.findViewById(R.id.what_say)).setTypeface(Typeface.createFromAsset(ctx.getAssets(),"NewCenSchCyrRom.ttf"));    
  
 
	

	
//	 bitmap = codec(bitmap, Bitmap.CompressFormat.JPEG, 8);
	
 //p=null;
    return view;
  }

 
// товар по позиции
  VK_User getProduct(int position) {
    return ((VK_User) getItem(position));
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
			VK_User p = getProduct((Integer)v.getTag());

			ViewParent t = v.getParent();
				//BitmapDrawable image = new BitmapDrawable(BitmapFactory.decodeByteArray(p.bmpplus, 0, p.bmpplus.length));	
				
			
		   		  
		   	
		

			//	TextView textView = (TextView)getListView().getChildAt((Integer)v.getTag());	
		//TextView tx = (TextView) t.findViewById(R.id.kolvo);	
		//tx.setText("fdfd");
		} 
		
	  };
}

