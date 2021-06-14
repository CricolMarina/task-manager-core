package com.stefanini.taskmanager.messagesender;

public class Message {
	private String text;
	private String address;
	
	public Message() {}
	
	public Message (String text, String addres) {
		this.text = text;
		this.address = addres;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getAddress() {
		return address;
	}
	public String getText() {
		return text;
	}
}
