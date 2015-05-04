package com.nerdroom.json;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import org.apache.http.HttpEntity;
//import com.google.api.client.http.HttpResponse;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.nerdroom.fcash.help.Account;
import com.nerdroom.fcash.help.md5;
import com.nerdroom.fcash.model.RegData;
import com.nerdroom.fcash.model.Response;
import com.nerdroom.fcash.model.ResponseSetting;
import com.nerdroom.fcash.model.articles;
import com.nerdroom.fcash.model.categories;
import com.nerdroom.funy.R;



import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

public class json_start {
	//final public static String api_path="http://api.fc-smm.com/";
	final public static String api_path="http://api.fc-smm.com/";

	public Gson gson=null;
	Context ctx;
	public json_start(Context ctx)
	{
		gson = new Gson();	
		this.ctx=ctx;
	}

	public String reg(RegData rg)
	{
		//Gson gson = new Gson();	
		Response rs;
		String pass=rg.password;
		rg.password=md5.md5(rg.password);
		String json = gson.toJson(rg);
		Log.i("tag",json);
		String path=api_path+"register/";
		try {
			rs=postData(path,json);
			if(rs!=null)
			if(rs.text.contains("successfully created"))
			{
				Account ac=new Account();
				ac.login=rg.login;
				ac.email=rg.email;
				ac.password=pass;
				ac.save(ctx);	
			return rs.text;
			}
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}	
		return rs.text;
		
		
	}
	public String login(RegData rg)
	{
		//Gson gson = new Gson();
		Response rs;
		String pass=rg.password;
		rg.password=md5.md5(rg.password);
		String json = gson.toJson(rg);
		Log.i("tag",json);
		String path=api_path+"login/";
		try {
			rs=postData(path,json);
			
			if(rs!=null) 
			{	
				if(rs.text.contains("password combination is not exists"))
					return rs.text;
				if(rs.user_id!=null)
			{
				Account ac=new Account();
				ac.restore(ctx);
				ac.user_id=rs.user_id;
				ac.token=rs.token;
				ac.login=rg.login;
				ac.email=rg.email;
				ac.password=pass;
				ac.save(ctx);
				
			}
			}	
			else return "";
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		
		return rs.text;
	}
	public Response postData(String path,String req) throws ClientProtocolException, IOException {
	    // Create a new HttpClient and Post Header
		String temp = null;
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost(path);
	    HttpResponse response=null;
	    
	        //Добавляем свои данные
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	   if(req!=null)
	   {
	        nameValuePairs.add(new BasicNameValuePair("request", req));
	     //   nameValuePairs.add(new BasicNameValuePair("stringdata", "AndDev is Cool!"));
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8));
	      //  httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	   }
	        // Выполняем HTTP Post Request
	        response = httpclient.execute(httppost);
	        temp = EntityUtils.toString(response.getEntity());
	       Log.i("tag", temp);	
	        Response rs=null;     
	        rs= gson.fromJson(temp, Response.class);
	        
	    return rs;
	} 
	public ResponseSetting postDataSetting(String path,String req) throws ClientProtocolException, IOException {
	    // Create a new HttpClient and Post Header
		String temp = null;
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost(path);
	    HttpResponse response=null;
	    
	        //Добавляем свои данные
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	   if(req!=null)
	   {
	        nameValuePairs.add(new BasicNameValuePair("request", req));
	     //   nameValuePairs.add(new BasicNameValuePair("stringdata", "AndDev is Cool!"));
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8));
	     //   httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	   }
	        // Выполняем HTTP Post Request
	        response = httpclient.execute(httppost);
	        temp = EntityUtils.toString(response.getEntity());
	       Log.i("tag", temp);	
	        ResponseSetting rs=null;     
	        rs= gson.fromJson(temp, ResponseSetting.class);
	    return rs;
	} 
	public String postDataString(String path,String req) throws ClientProtocolException, IOException {
	
		String temp = null;
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost(path);
	    HttpResponse response=null;
	    
	        //Добавляем свои данные
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	   if(req!=null)
	   {
	       
		   nameValuePairs.add(new BasicNameValuePair("request", req));
	     //   nameValuePairs.add(new BasicNameValuePair("stringdata", "AndDev is Cool!"));
		   httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8));
	      //  httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	   }
	        // Выполняем HTTP Post Request
	        response = httpclient.execute(httppost);
	        HttpEntity en = response.getEntity();
	   //     InputStream t=en.getContent();
	        temp = EntityUtils.toString(en);
	  //     Log.i("tag", temp);	
	         
	       
	    return temp;
	} 
		
	
	public  String connect(String url)
	{
		
		
		
		
		
		
		String result = null;
	    HttpClient httpclient = new DefaultHttpClient();

	    // Prepare a request object
	    HttpGet httpget = new HttpGet(url); 

	    // Execute the request
	    HttpResponse response;
	    try {
	        response = httpclient.execute(httpget);
	        // Examine the response status
	        Log.i("Praeda",response.getStatusLine().toString());

	        // Get hold of the response entity
	        HttpEntity entity = response.getEntity();
	        // If the response does not enclose an entity, there is no need
	        // to worry about connection release

	        if (entity != null) {

	            // A Simple JSON Response Read
	            InputStream instream = entity.getContent();
	            result= convertStreamToString(instream);
	            // now you have the string representation of the HTML request
	            instream.close();
	           
	        }


	    } catch (Exception e) {return null;}
		return result;
	}

	    private static String convertStreamToString(InputStream is) {
	    /*
	     * To convert the InputStream to String we use the BufferedReader.readLine()
	     * method. We iterate until the BufferedReader return null which means
	     * there's no more data to read. Each line will appended to a StringBuilder
	     * and returned as String.
	     */
	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    StringBuilder sb = new StringBuilder();

	    String line = null;
	    try {
	        while ((line = reader.readLine()) != null) {
	            sb.append(line + "\n");
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            is.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    return sb.toString();
	}
	  
public void ssh() throws NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException, UnrecoverableKeyException, KeyManagementException {
	SSLContext sslContext = SSLContext.getDefault();

	KeyStore trustSt = KeyStore.getInstance("BKS");
	TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
	InputStream trustStoreStream = ctx.getResources().openRawResource(R.raw.truststore);
	trustSt.load(trustStoreStream, "<yourpassword>".toCharArray());
	
	trustManagerFactory.init(trustSt);

	KeyStore keyStore = KeyStore.getInstance("BKS");
	KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
	InputStream keyStoreStream = ctx.getResources().openRawResource(R.raw.keystore);
	keyStore.load(keyStoreStream, "<yourpassword>".toCharArray());
	keyManagerFactory.init(keyStore, "<yourpassword>".toCharArray());

	sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);
}

}
