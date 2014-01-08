package com.ncs.nsp.sensing.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="SBI_USER")
public class SBIUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4542072755660954649L;

	@Id
	private long id;
	
	@Column(nullable=false,columnDefinition="varchar2(100 char)")
	private String user_id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}
