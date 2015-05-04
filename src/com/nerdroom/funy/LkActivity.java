package com.nerdroom.funy;

import com.nerdroom.fcash.help.Account;
import com.nerdroom.fcash.help.MyActivity;
import com.nerdroom.json.LkLouder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LkActivity  extends MyActivity {
Button btn_subsribers,btn_get_maney,btn_maney,btn_change_pass;
public static String name;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lk);
		Account ac;
		ac=new Account();
		ac.restore(this);
		new LkLouder(this).execute();
		set_button();
		
		
	}
	public void set_button()
	{
		btn_subsribers=wg.get_b(R.id.writed);
		btn_get_maney=wg.get_b(R.id.get_money);
		btn_maney=wg.get_b(R.id.schet);
		btn_change_pass=wg.get_b(R.id.btn_change_pass);
		btn_change_pass.setOnClickListener(new OnClickListener() {
			 
			  public void onClick(View arg0) {
				  Intent intent = new Intent(mn, ChangePassActivity.class);
			       	
			       	
			       	 mn.startActivity(intent);
			    	  
				  }
		 
				});
		btn_subsribers.setOnClickListener(new OnClickListener() {
			 
			  public void onClick(View arg0) {
				  Intent intent = new Intent(mn, SubscribersActivity.class);
			       	
			       	
			       	 mn.startActivity(intent);
			    	  
				  }
		 
				});
		btn_get_maney.setOnClickListener(new OnClickListener() {
			 
			  public void onClick(View arg0) {
				  Intent intent = new Intent(mn, GetMoneyActivity.class);
			       	
				 	 intent.putExtra("name", name);	
			       	 mn.startActivity(intent);
			    	  
				  }
		 
				});
		btn_maney.setOnClickListener(new OnClickListener() {
			 
			  public void onClick(View arg0) {
				  Intent intent = new Intent(mn, BalanceActivity.class);
			       	
			       	
			       	 mn.startActivity(intent);
			    	  
				  }
		 
				});
	}
	
}
