package com.wallacomic.domain;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Mensaje {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne
	private Usuario user; //User who writes
	
	private String message;
	
	protected Mensaje(){}
	public Mensaje(Usuario u, String m){
		this.user = u;
		this.message = m;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Usuario getUser() {
		return user;
	}
	public void setUser(Usuario user) {
		this.user = user;
	}
	public String getMessage() {
		return message;
	}
	public void setComentarios(String m) {
		this.message = m;
	}
}
