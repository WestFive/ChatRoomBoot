package com.dub.spring.chat;


// POJO
public class MyMessage {
	 
    private String from;// sender
    private String text;//
    //private boolean first;// first message after connection
    
    
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

}