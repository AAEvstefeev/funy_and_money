package com.nerdroom.funy;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import com.nerdroom.fcash.adapter.MenuAdapter;
import com.nerdroom.fcash.adapter.TopAdapter;
import com.nerdroom.fcash.help.MyActivity;
import com.nerdroom.fcash.model.TopUser;
import com.nerdroom.json.TopLouder;

public class TopActivity extends MyActivity {
	Context ctx;
	TopActivity mn;
	protected void onCreate(Bundle savedInstanceState) {
		//    setTheme(R.style.Theme_Sherlock);
		    super.onCreate(savedInstanceState);
		    setContentView(R.layout.top);
	ctx=this;	    
	mn=this;
	new TopLouder(mn).execute();
	//set_top();
	}
	public void set_top()
	{
		 ArrayList<TopUser> top_list=new ArrayList<com.nerdroom.fcash.model.TopUser>();
		 
	     top_list.add(new TopUser("Вася",100032));
	     top_list.add(new TopUser("Федар",500));
	     top_list.add(new TopUser("Анатолий",170));
	     top_list.add(new TopUser("Марина",10));
	     top_list.add(new TopUser("Алексей",0));
	     

	     TopAdapter adapter = new TopAdapter(this, top_list);	
	     ListView list_m = (ListView) findViewById(R.id.top_list);
	     list_m.setAdapter(adapter);
		
	}
}
