package com.nerdroom.fcash.vk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nerdroom.fcash.adapter.VkAdapter;
import com.nerdroom.fcash.help.MyActivity;
import com.nerdroom.fcash.help.VK_User;
import com.nerdroom.fcash.model.RegData;
import com.nerdroom.funy.MenuActivity;
import com.nerdroom.funy.R;
import com.nerdroom.json.LoginLouder;
import com.nerdroom.json.MenuLouder;
import com.perm.kate.api.Api;
import com.perm.kate.api.FriendsList;
import com.perm.kate.api.User;
import com.samir.ContactBean;
import com.samir.ContactListActivity;
import com.samir.ContanctAdapter;

public class VkFriendsActivity extends MyActivity implements android.view.View.OnClickListener  {
	Account account=new Account();
    Api api;
    VkFriendsActivity mn;
	private Button search_btn,send_all;
	private EditText search_edt;
	private ProgressBar bar;
	 ListView list_m;
	ArrayList<VK_User> lu;
    private final int REQUEST_LOGIN=1;
    VkAdapter adapter;
    boolean all=false;
	protected void onCreate(Bundle savedInstanceState) {
		//    setTheme(R.style.Theme_Sherlock);
		    super.onCreate(savedInstanceState);
		mn=this;
		lu=new ArrayList<VK_User> ();
		 setContentView(R.layout.vk_list);
		send_all = (Button) findViewById(R.id.send_to_all);
		list_m = (ListView) mn.findViewById(R.id.vk_list);
		search_btn = (Button) findViewById(R.id.search_btn);
		search_edt = (EditText) findViewById(R.id.search_edt);
		bar = (ProgressBar) findViewById(R.id.bar);   
		search_btn.setOnClickListener(this);
		    account.restore(this);
		      if(account.access_token!=null)
                 {
      		  
      		   api=new Api(account.access_token, Constants.API_ID);
      		//   postToWall();
      		  get_friends();
      		  
                 }
      	   else
      	   		{
      		   startLoginActivity();   
      	   		}
		    
		      send_all.setOnClickListener(new View.OnClickListener() 
			    {
			    	public void onClick(View v) 
			    	{
			    	if(all) all=false; else all=true;
			    		//showCallDialogAll();
			    		
			    	for(int i=0;i<lu.size();i++)
			    		{
			    			lu.get(i).checked=all;
			    		}
			    	adapter = new VkAdapter(mn, lu);	 
					  list_m.setAdapter(adapter);
			     } });	
		    //get_friends();
		      search_edt.addTextChangedListener(new TextWatcher(){

				    @Override
				    public void afterTextChanged(Editable arg0) {
				        // TODO Auto-generated method stub
				    	String st_search=arg0.toString();
				    	ArrayList<VK_User> search_list = new ArrayList<VK_User>();
						if (null != lu && lu.size() != 0) {
							int i=0;
							while(i<lu.size())
							{
								String st=lu.get(i).user.first_name.toLowerCase()+lu.get(i).user.last_name.toLowerCase();
									//	+lu.get(i).user.nickname.toLowerCase();
							if(st.contains(st_search.toLowerCase()))	
								{search_list.add(lu.get(i)); }
							i++;	
							}
							 
							  adapter = new VkAdapter(mn, search_list);	 
							  list_m.setAdapter(adapter);
					       	  	

						}
				    }

				    @Override
				    public void beforeTextChanged(CharSequence s, int start, int count,
				            int after) {
				        // TODO Auto-generated method stub

				    }

				    @Override
				    public void onTextChanged(CharSequence s, int start, int before, int count) {
				        // TODO Auto-generated method stub

				    }

				});		    
}
	public void set_menu()
	{
		
		
	
		
	}
	
	  private void get_friends() {
	        //Общение с сервером в отдельном потоке чтобы не блокировать UI поток
	        new Thread(){
	            @Override
	            public void run(){
	                try {
	                	
	                	
	                	ArrayList<User> l=  api.getFriends(account.user_id, "uid, first_name, last_name, nickname, photo, photo_200", null, "String captcha_key", "String captcha_sid");
	                	 int i = 0;
	                	    while(i<l.size()){
	                	    	VK_User u=new VK_User(l.get(i));

	                	    lu.add(u);  
	                	    i++;
	                	    }
	                	adapter = new VkAdapter(mn, lu);	
	            	   
	                	//createWallPost(account.user_id, ";
	                    //Показать сообщение в UI потоке 
	                    runOnUiThread(successRunnable);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        }.start();
	    }
	      
	    
	    public void postToWall(final long own) {
	        //Общение с сервером в отдельном потоке чтобы не блокировать UI поток
	        new Thread(){
	            @Override
	            public void run(){
	            	com.nerdroom.fcash.help.Account ac=new com.nerdroom.fcash.help.Account();
	            	ac.restore(mn);
	                try {
	                   long owner = 0;
	                   if(own==-1)  owner= account.user_id;
	                		else owner=own;
	                	String spam_text=getString(R.string.spam_text1);
                		if(ac.ref!=null)spam_text=spam_text+getString(R.string.spam_text2)+ac.ref;
                		spam_text=spam_text+getString(R.string.spam_text3); 
	                    api.createWallPost(owner, spam_text, null, null, false, false, false, null, null, null, null, null);
	                  

	                    //Показать сообщение в UI потоке 
	                    runOnUiThread(successRunnable);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        }.start();
	    }
	    Runnable successRunnable=new Runnable(){
	        @Override
	        public void run() {
	         //   Toast.makeText(getApplicationContext(), "Запись успешно добавлена", Toast.LENGTH_LONG).show();
	            
       	     list_m.setAdapter(adapter);
       	     bar.setVisibility(View.GONE);
	        }
	    };
	    private void startLoginActivity() {
	        Intent intent = new Intent();
	        intent.setClass(this, VKActivity.class);
	        startActivityForResult(intent, REQUEST_LOGIN);
	    }
	    private void logOut() {
	        api=null;
	        account.access_token=null;
	        account.user_id=0;
	        account.save(VkFriendsActivity.this);
	        
	    }
	    @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        if (requestCode == REQUEST_LOGIN) {
	            if (resultCode == RESULT_OK) {
	                //авторизовались успешно 
	                account.access_token=data.getStringExtra("token");
	                account.user_id=data.getLongExtra("user_id", 0);
	                account.save(VkFriendsActivity.this);
	                api=new Api(account.access_token, Constants.API_ID);
	                
	            }
	        }
	        
	     
	    }
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			int i=0;
			int count=0;
			String name=null;
			while(i<lu.size())
			{
				VK_User bean = lu.get(i);
			if(bean.checked)
				{count++;
				if(name==null) name=bean.user.first_name;
				else name=name+", "+bean.user.first_name;
				}
			i++;
			}
			if(count>0)
			if(count<4)
			{	
			showCallDialog(name);
			}
			else 
				showCallDialog(getString(R.string.fr));	
		}
		private void showCallDialog(String name) {
			AlertDialog alert = new AlertDialog.Builder(VkFriendsActivity.this)
					.create();
			alert.setTitle(getString(R.string.invite_fcash));

			alert.setMessage(getString(R.string.shure_invite)+" "+ name + " ?");

			alert.setButton(getString(R.string.no), new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			alert.setButton2(getString(R.string.yes), new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
				//	String phoneNumber = "tel:" + phoneNo;
			//		Intent intent = new Intent(Intent.ACTION_CALL, Uri
				//			.parse(phoneNumber));
			//		startActivity(intent);
					int i=0;
		    		while(i<lu.size())
		    		{
		    			VK_User bean = lu.get(i);
		    		if(bean.checked) postToWall(bean.user.uid);
		    		i++;
		    		}
					
				}
			});
			alert.show();
		}
		private void showCallDialogAll() {
			AlertDialog alert = new AlertDialog.Builder(VkFriendsActivity.this)
					.create();
			alert.setTitle(getString(R.string.invite_fcash));

			alert.setMessage(getString(R.string.shure_invite)+" "+ getString(R.string.all_fr) + " ?");

			alert.setButton(getString(R.string.no), new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			alert.setButton2(getString(R.string.yes), new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
				//	String phoneNumber = "tel:" + phoneNo;
			//		Intent intent = new Intent(Intent.ACTION_CALL, Uri
				//			.parse(phoneNumber));
			//		startActivity(intent);
					int i=0;
		    		while(i<lu.size())
		    		{
		    			VK_User bean = lu.get(i);
		    		 postToWall(bean.user.uid);
		    		i++;
		    		}
					
				}
			});
			alert.show();
		}
}