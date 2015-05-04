package com.nerdroom.funy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.nerdroom.fcash.help.MyActivity;
import com.nerdroom.fcash.model.MyMenuItem;
import com.nerdroom.fcash.vk.Account;
import com.nerdroom.fcash.vk.Constants;
import com.nerdroom.fcash.vk.VKActivity;
import com.nerdroom.fcash.vk.VkFriendsActivity;
import com.nerdroom.funy.R;
import com.perm.kate.api.Api;

public class InviteActivity extends MyActivity {
	Button btn_vk_repost,btn_vk_invite,btn_facebook_repost,btn_facebook_invite,btn_sms_invite;

	
	Account account=new Account();
    Api api;
    private final int REQUEST_LOGIN=1;
	protected void onCreate(Bundle savedInstanceState) {
		//    setTheme(R.style.Theme_Sherlock);
		
		
		    super.onCreate(savedInstanceState);
		    setContentView(R.layout.invite_activity);
	set_btn();
	}
	
private void set_btn()
	{
	btn_vk_invite=wg.get_b(R.id.invite_vk);
	//btn_facebook_invite=wg.get_b(R.id.invite_facebook);
	btn_sms_invite=wg.get_b(R.id.invite_sms);
	btn_vk_repost=wg.get_b(R.id.repost_vk);
	
	 btn_vk_invite.setOnClickListener(new OnClickListener() {
		 
			  public void onClick(View arg0) {
				  Intent intent = new Intent(mn, VkFriendsActivity.class);
			       	
			       	
			       	 mn.startActivity(intent);
			    	  
				  }
		 
				});	
	 btn_sms_invite.setOnClickListener(new OnClickListener() {
		 
		  public void onClick(View arg0) {
			  Intent intent = new Intent(mn, com.samir.ContactListActivity.class);
		       	
		       	
		       	 mn.startActivity(intent);
		    	  
			  }
	 
			});

	 
	 btn_vk_repost.setOnClickListener(new OnClickListener() {
		 
		
		  public void onClick(View arg0) {	  
			  account.restore(mn);  
			  if(account.access_token!=null)
              {
				  api=new Api(account.access_token, Constants.API_ID);  
              }
			  else 
			  {
				  startLoginActivity();
			  }
		  //Ћбщение с сервером в отдельном потоке чтобы не блокировать UI поток
	        new Thread(){
	            @Override
	            public void run(){
	                try {
	                	com.nerdroom.fcash.help.Account ac=new com.nerdroom.fcash.help.Account();
	                	ac.restore(mn);
	                	String spam_text=getString(R.string.spam_text1);
                		if(ac.ref!=null)spam_text=spam_text+getString(R.string.spam_text2)+ac.ref;
                		spam_text=spam_text+getString(R.string.spam_text3); 
	                    api.createWallPost(account.user_id, spam_text, null, null, false, false, false, null, null, null, null, null);
	                    //Џоказать сообщение в UI потоке 
	                   runOnUiThread(successRunnable);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        }.start();   
			    
		    	  
		  }  
	 
			});	
	}

Runnable successRunnable=new Runnable(){
    @Override
    public void run() {
        Toast.makeText(getApplicationContext(), "‡апись успешно добавлена", Toast.LENGTH_LONG).show();
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
    account.save(InviteActivity.this);
    
}
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == REQUEST_LOGIN) {
        if (resultCode == RESULT_OK) {
            //авторизовались успешно 
            account.access_token=data.getStringExtra("token");
            account.user_id=data.getLongExtra("user_id", 0);
            account.save(InviteActivity.this);
            api=new Api(account.access_token, Constants.API_ID);
            
        }
    }
}
}
