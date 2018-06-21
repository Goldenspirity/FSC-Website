package com.sdzee.beans;

public class News {
	
	private int id;
	private String title;
	private String content;
	private String image;
	private String date;
	private String lastEdit;
	private String summary;
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String t) {
		this.title = t;
	}
	
	public String getContent() {
		return this.content;
	}
	public void setContent(String text) {
		this.content = text;
	}
	
	public String getImage() {
		return this.image;
	}
	public void setImage(String url) {
		this.image = url;
	}
	
	public String getDate() {
		return this.date;
	}
	public void setDate (String d) {
		this.date = d;
	}
	
	public String getLastEdit() {
		return this.lastEdit;
	}
	public void setLastEdit (String d) {
		this.lastEdit = d;
	}
	
	public String getSummary() {
		return this.summary;
	}
	public void setSummary (String d) {
		this.summary = d;
	}

}
