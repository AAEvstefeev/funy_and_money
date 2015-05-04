package com.samir;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.nerdroom.fcash.adapter.VkAdapter;
import com.nerdroom.fcash.help.MyActivity;
import com.nerdroom.fcash.help.VK_User;
import com.nerdroom.fcash.vk.VkFriendsActivity;
import com.nerdroom.funy.R;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ContactListActivity extends MyActivity implements
		 android.view.View.OnClickListener {
	 private static final String SMS_SEND_ACTION = "CTS_SMS_SEND_ACTION";
	    private static final String SMS_DELIVERY_ACTION = "CTS_SMS_DELIVERY_ACTION";
	private ListView listView;
	private List<ContactBean> list = new ArrayList<ContactBean>();
	private Button search_btn,send_all;
	private EditText search_edt;
	boolean all=false;
	Context ctx;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sms_activity);
		ctx=this;
		listView = (ListView) findViewById(R.id.list);
		search_btn = (Button) findViewById(R.id.search_btn);
		send_all = (Button) findViewById(R.id.send_to_all);
		search_edt = (EditText) findViewById(R.id.search_edt);
	     send_all.setOnClickListener(new View.OnClickListener() 
		    {
		    	public void onClick(View v) 
		    	{
		    	//	showCallDialogAll();
		    		if(all) all=false; else all=true;
		    		//showCallDialogAll();
		    		
		    	for(int i=0;i<list.size();i++)
		    		{
		    			list.get(i).checked=all;
		    		}
		    	ContanctAdapter objAdapter = new ContanctAdapter(
						ContactListActivity.this, R.layout.alluser_row, list);
				listView.setAdapter(objAdapter);
		     } });
		search_edt.addTextChangedListener(new TextWatcher(){

		    @Override
		    public void afterTextChanged(Editable arg0) {
		        // TODO Auto-generated method stub
		    	String st_search=arg0.toString();
		    	ArrayList<ContactBean> search_list = new ArrayList<ContactBean>();
				if (null != list && list.size() != 0) {
					int i=0;
					while(i<list.size())
					{
						
					if(list.get(i).getName().toLowerCase().contains(st_search.toLowerCase()))	
						{search_list.add(list.get(i)); }
					i++;	
					}
				ContanctAdapter objAdapter = new ContanctAdapter(
						ContactListActivity.this, R.layout.alluser_row, search_list);
				listView.setAdapter(objAdapter);

				
					Collections.sort(list, new Comparator<ContactBean>() {

						@Override
						public int compare(ContactBean lhs, ContactBean rhs) {
							
							return lhs.getName().compareTo(rhs.getName());
							
						}
					});
				

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
		search_btn.setOnClickListener(this);
		Cursor phones = getContentResolver().query(
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null,
				null, null);
		while (phones.moveToNext()) {

			String name = phones
					.getString(phones
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

			String phoneNumber = phones
					.getString(phones
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

			ContactBean objContact = new ContactBean();
			objContact.setName(name);
			objContact.setPhoneNo(phoneNumber);
			list.add(objContact);

		}
		phones.close();

		ContanctAdapter objAdapter = new ContanctAdapter(
				ContactListActivity.this, R.layout.alluser_row, list);
		listView.setAdapter(objAdapter);

		if (null != list && list.size() != 0) {
			Collections.sort(list, new Comparator<ContactBean>() {

				@Override
				public int compare(ContactBean lhs, ContactBean rhs) {
					return lhs.getName().compareTo(rhs.getName());
				}
			});
			/**AlertDialog alert = new AlertDialog.Builder(
					ContactListActivity.this).create();
			alert.setTitle("");

			alert.setMessage(list.size() + " Contact Found!!!");

			alert.setButton("OK", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			alert.show();
**/
		} else {
			showToast(getString(R.string.no_contact));
		}
	}

	private void showToast(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}



	private void showCallDialog(String name) {
		AlertDialog alert = new AlertDialog.Builder(ContactListActivity.this)
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
				com.nerdroom.fcash.help.Account ac=new com.nerdroom.fcash.help.Account();
            	ac.restore(mn);
				String spam_text=getString(R.string.spam_text1);
        		if(ac.ref!=null)spam_text=spam_text+getString(R.string.spam_text2)+ac.ref;
        		spam_text=spam_text+getString(R.string.spam_text3); 
				int i=0;
				while(i<list.size())
				{
					
					ContactBean bean = list.get(i);
					if(bean.checked)
						sendSMS(bean.getPhoneNo(), spam_text);
					i++;
				}
				;
			}
		});
		alert.show();
	}
	
	private void sendSMS(String phoneNumber, String message)
	{
		 SmsManager sm = SmsManager.getDefault();

	        IntentFilter sendIntentFilter = new IntentFilter(SMS_SEND_ACTION);
	        IntentFilter receiveIntentFilter = new IntentFilter(SMS_DELIVERY_ACTION);

	        PendingIntent sentPI = PendingIntent.getBroadcast(ctx, 0,new Intent(SMS_SEND_ACTION), 0);
	        PendingIntent deliveredPI = PendingIntent.getBroadcast(ctx, 0,new Intent(SMS_DELIVERY_ACTION), 0);

	        BroadcastReceiver messageSentReceiver = new BroadcastReceiver()
	        {      
	            @Override
	            public void onReceive(Context context, Intent intent)
	            {
	                switch (getResultCode())
	                {
	                    case Activity.RESULT_OK:
	                        Toast.makeText(context, getString(R.string.sms_send), Toast.LENGTH_SHORT).show();
	                        break;
	                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
	                        Toast.makeText(context, getString(R.string.error), Toast.LENGTH_SHORT).show();
	                        break;
	                    case SmsManager.RESULT_ERROR_NO_SERVICE:
	                        Toast.makeText(context, getString(R.string.network_denied), Toast.LENGTH_SHORT).show();
	                        break;
	                    case SmsManager.RESULT_ERROR_NULL_PDU:
	                        Toast.makeText(context, getString(R.string.error), Toast.LENGTH_SHORT).show();
	                        break;
	                    case SmsManager.RESULT_ERROR_RADIO_OFF:
	                        Toast.makeText(context, getString(R.string.error), Toast.LENGTH_SHORT).show();
	                        break;
	                }
	            }
	        };

	        registerReceiver(messageSentReceiver, sendIntentFilter);

	        BroadcastReceiver messageReceiveReceiver = new BroadcastReceiver()
	        {      
	            @Override
	            public void onReceive(Context arg0, Intent arg1)
	            {
	                switch (getResultCode())
	                {
	                    case Activity.RESULT_OK:
	                        Toast.makeText(getBaseContext(), getString(R.string.sms_done),Toast.LENGTH_SHORT).show();
	                        break;
	                    case Activity.RESULT_CANCELED:
	                        Toast.makeText(getBaseContext(), getString(R.string.sms_not_done), Toast.LENGTH_SHORT).show();
	                    break;                        
	                }
	            }
	        };

	        registerReceiver(messageReceiveReceiver, receiveIntentFilter);

	        ArrayList<String> parts =sm.divideMessage(message);

	        ArrayList<PendingIntent> sentIntents = new ArrayList<PendingIntent>();
	        ArrayList<PendingIntent> deliveryIntents = new ArrayList<PendingIntent>(); 

	        for (int i = 0; i < parts.size(); i++)
	        {
	            sentIntents.add(PendingIntent.getBroadcast(ctx, 0, new Intent(SMS_SEND_ACTION), 0));
	            deliveryIntents.add(PendingIntent.getBroadcast(ctx, 0, new Intent(SMS_DELIVERY_ACTION), 0));
	        }

	        sm.sendMultipartTextMessage(phoneNumber,null, parts, sentIntents, deliveryIntents);
	    }
	
	 //---sends an SMS message to another device---
    

	
	

	@Override
	public void onClick(View v) {
		int i=0;
		int count=0;
		String name=null;
		while(i<list.size())
		{
			ContactBean bean = list.get(i);
		if(bean.checked)
			{count++;
			if(name==null) name=bean.getName();
			else name=name+", "+bean.getName();
			}
		i++;
		}
		if(count>0)
		if(count<4)
		{	
		showCallDialog(name);
		}
		else 
			showCallDialog("друзей");
	}
	public static List<ContactBean> cloneList(List<ContactBean> list) {
	    List<ContactBean> clone = new ArrayList<ContactBean>(list.size());
	    for(ContactBean item: list) clone.add(item.clone());
	    return clone;
	}
	
	private void showCallDialogAll() {
		AlertDialog alert = new AlertDialog.Builder(ContactListActivity.this)
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
				com.nerdroom.fcash.help.Account ac=new com.nerdroom.fcash.help.Account();
            	ac.restore(mn);
             	String spam_text=getString(R.string.spam_text1);
        		if(!ac.ref.equals(""))spam_text=spam_text+getString(R.string.spam_text2)+ac.ref;
        		spam_text=spam_text+getString(R.string.spam_text3); 
				int i=0;
	    		while(i<list.size())
	    		{
	    			ContactBean bean = list.get(i);
	    			sendSMS(bean.getPhoneNo(), spam_text);
	    		i++;
	    		}
				
			}
		});
		alert.show();
	}	
}
