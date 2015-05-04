package com.nerdroom.fcash.help;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class Account {
    public String token;
    public String user_id;
    public String password;
    public String email;
    public String login;
    public String ref;
    public boolean firts_time=false;
    
    public void save(Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Editor editor=prefs.edit();
        editor.putString("token", token);
        editor.putString("user_id", user_id);
        editor.putString("password",password );
        editor.putString("email",email );
        editor.putString("login",login);
        editor.putString("ref",ref);
        editor.commit();
    }
    public void clear(Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Editor editor=prefs.edit();
        editor.putString("token", null);
        editor.putString("user_id", null);
        editor.putString("password",null );
        editor.putString("email",null );
        editor.putString("login",null);
        editor.putString("ref",null);
        editor.commit();
    }
    
    public void restore(Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        token=prefs.getString("token", null);
        user_id=prefs.getString("user_id", null);
        password=prefs.getString("password", null);
        login=prefs.getString("login", null);
        email=prefs.getString("email", null);
        ref=prefs.getString("ref", null);
        firts_time=prefs.getBoolean("first_time", false);
    }
    public void set_begin(Context context)
    {
    	  SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
          Editor editor=prefs.edit();
          editor.putBoolean("first_time", true);
          editor.commit();
    }
}
