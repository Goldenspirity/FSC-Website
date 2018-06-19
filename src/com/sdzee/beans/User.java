package com.sdzee.beans;

import java.sql.Timestamp;

public class User {
	
	private Long id;
	private String email;
	private byte[] password;
	private String name;
	private Timestamp registerDate;
	private String role;
	
	public void setEmail (String str) {
		this.email = str;
	}
	public void setPassword (byte[] str) {
		this.password = str;
	}
	public void setName (String str) {
		this.name = str;
	}
	public void setId (Long id) {
		this.id = id;
	}
    public void setRegisterDate( Timestamp registerDate ) {
        this.registerDate = registerDate;
    }
    public void setRole (String role) {
    	this.role = role;
    }
	public String getEmail() {
		return this.email;
	}
	public byte[] getPassword() {
		return this.password;
	}
	public String getName() {
		return this.name;
	}
	public Long getId() {
		return this.id;
	}
    public Timestamp getRegisterDate() {
        return this.registerDate;
    }
    public String getRole() {
    	return this.role;
    }
}
