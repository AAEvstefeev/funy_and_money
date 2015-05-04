package com.nerdroom.fcash.vk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class Account {
    public String access_token;
    public long user_id;
    
    public void save(Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Editor editor=prefs.edit();
        editor.putString("vk_access_token", access_token);
        editor.putLong("vk_user_id", user_id);
        editor.commit();
    }
    
    public void restore(Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        access_token=prefs.getString("vk_access_token", null);
        user_id=prefs.getLong("vk_user_id", 0);
    }
}
