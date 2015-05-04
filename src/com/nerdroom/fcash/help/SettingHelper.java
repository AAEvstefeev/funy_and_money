package com.nerdroom.fcash.help;

import com.nerdroom.fcash.model.RegData;
import com.nerdroom.fcash.model.Setting;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SettingHelper extends Component{

public SettingHelper(Activity _mn) {
		super(_mn);
		// TODO Auto-generated constructor stub
	}
public void save_reg_data(RegData s)
{
	//SharedPreferences settings = mn.getApplicationContext().getSharedPreferences("RegData", 0);
	 SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(mn.getApplicationContext());
	SharedPreferences.Editor editor = settings.edit();
	editor.putString("name",s.name);
	editor.putString("password",s.password);
	editor.putString("email",s.email);
	editor.putString("referer",s.referer);
	editor.commit();
}
public RegData get_reg_data()
{
//	SharedPreferences settings = mn.getApplicationContext().getSharedPreferences("RegData", 0);
	SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(mn.getApplicationContext());
	String login=settings.getString("name", "").toString();
	String pass=settings.getString("password", "").toString();
	String mail=settings.getString("email", "").toString();
	String ref=settings.getString("referer", "").toString();
	RegData s=new RegData(login,pass,mail);
	if(!ref.equals(""))
		s.referer=ref;
	return s;
	
}
public void save_setting(Setting s)
{
	SharedPreferences settings = mn.getSharedPreferences("UserInfo", 0);
	SharedPreferences.Editor editor = settings.edit();
	editor.putString("name",s.name);
	editor.putString("telefon",s.telefon);
	editor.putString("mail",s.mail);
	editor.commit();
}
public Setting get_setting()
{
	SharedPreferences settings = mn.getSharedPreferences("UserInfo", 0);
	
	String name=settings.getString("name", "").toString();
	String mail=settings.getString("mail", "").toString();
	String telefon=settings.getString("telefon", "").toString();
	String vk=settings.getString("vk", "").toString();
	Setting s=new Setting(name,telefon,mail);
	return s;
	
}

public void save_art_number(String id_cat, int id_art)
{
	//SharedPreferences settings = mn.getApplicationContext().getSharedPreferences("RegData", 0);
	 SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(mn.getApplicationContext());
	SharedPreferences.Editor editor = settings.edit();
	editor.putInt("cat_"+id_cat,id_art);
	editor.commit();
}
public int get_art_number(String id_cat)
{
	SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(mn.getApplicationContext());
	int s=settings.getInt("cat_"+id_cat, -100);
	return s;	
}


}
