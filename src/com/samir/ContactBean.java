package com.samir;

public class ContactBean {
	private String name;
	private String phoneNo;
	public boolean checked=false;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	protected ContactBean clone(){
		ContactBean c= new ContactBean();
		c.name=this.name;
		c.phoneNo=this.phoneNo;
		return c;
}
	
}
