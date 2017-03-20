package com.wallacomic.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TB_MENSAJE")
public class Mensaje {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne
	@NotNull
	private Usuario userMensaje; //User who writes
	
	@NotNull
	private String message;
	
	protected Mensaje(){}
	public Mensaje(Usuario u, String m){
		this.userMensaje = u;
		this.message = m;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Usuario getUser() {
		return userMensaje;
	}
	public void setUser(Usuario user) {
		this.userMensaje = user;
	}
	public String getMessage() {
		return message;
	}
	public void setComentarios(String m) {
		this.message = m;
	}
}
