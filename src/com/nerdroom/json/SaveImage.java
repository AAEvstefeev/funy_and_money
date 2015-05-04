package com.nerdroom.json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.nerdroom.fcash.help.MyWebView;
import com.nerdroom.funy.WorkActivity;



import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class SaveImage extends AsyncTask<Void, Void, Void> {
String url;
MyWebView ctx;

boolean load=false;
	public SaveImage(String url,MyWebView ctx) 
	{
		this.url=url;
		this.ctx=ctx;
	}
	
    @Override
    protected void onPreExecute() {
      super.onPreExecute();
    
    }

    @Override
    protected Void doInBackground(Void... params) {
    	load=download(url);
      return null;
    }

    @Override
    protected void onPostExecute(Void result) {
      super.onPostExecute(result);
      if(load) Toast.makeText(ctx.getContext(), "Изображение сохранено", Toast.LENGTH_LONG).show();
      else Toast.makeText(ctx.getContext(), "Ошибка", Toast.LENGTH_LONG).show();
	   		} 
  
    Bitmap loadImage(String image_location){
        
        URL imageURL = null;
         
        try {
         imageURL = new URL(image_location);
         } 
         
        catch (MalformedURLException e) {
            e.printStackTrace();
         }
         
        Bitmap bitmap=null;
    	try {
         HttpURLConnection connection= (HttpURLConnection)imageURL.openConnection();
         connection.setDoInput(true);
         connection.connect();
            InputStream inputStream = connection.getInputStream();
              
            bitmap = BitmapFactory.decodeStream(inputStream);//Convert to bitmap
           // image_view.setImageBitmap(bitmap);
        }
        catch (IOException e) {
             
             e.printStackTrace();
        }
    	
    	WorkActivity.downlouding=false;
        return bitmap; 
        
        
    }
    
    public boolean download(String url)
    {
    	Bitmap bitmap=loadImage(url);
    	if(bitmap==null) return false;
    	String extr = Environment.getExternalStorageDirectory().toString();
        File mFolder = new File(extr + "/fcash");

        if (!mFolder.exists()) {
            mFolder.mkdir();
        }

        String strF = mFolder.getAbsolutePath();
        File mSubFolder = new File(strF + "/pic");

        if (!mSubFolder.exists()) {
            mSubFolder.mkdir();
        }

        String s = ctx.art_id+".png";

        File f = new File(mSubFolder.getAbsolutePath(),s);
        String strMyImagePath = f.getAbsolutePath();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(f);
            
    		bitmap.compress(Bitmap.CompressFormat.PNG,70, fos);

            fos.flush();
            fos.close();
         //   MediaStore.Images.Media.insertImage(getContentResolver(), b, "Screen", "screen");
        }catch (FileNotFoundException e) {

            e.printStackTrace();
            return false;
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    	
    	return true;	
    }
  }

