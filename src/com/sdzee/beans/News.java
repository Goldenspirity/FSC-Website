package com.sdzee.beans;

import java.sql.Timestamp;

public class News {
	
	private int id;
	private String title;
	private String content;
	private String image;
	private Timestamp date;
	
	public int getId() {
		return this.id;
	}

}
