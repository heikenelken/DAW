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
public class Conversacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne
	private Usuario userBuyer; //User que compra
	@OneToOne
	private Usuario userSeller; //Usuario que vende
	
	@ElementCollection
	private List<String> comentarios;
	
	protected Conversacion(){}
	public Conversacion(Usuario userBuyer, Usuario userSeller, List<String> comentarios){
		this.userBuyer=userBuyer;
		this.userSeller=userSeller;
		this.comentarios=comentarios;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Usuario getUserBuyer() {
		return userBuyer;
	}
	public void setUserBuyer(Usuario userBuyer) {
		this.userBuyer = userBuyer;
	}
	public Usuario getUserSeller() {
		return userSeller;
	}
	public void setUserSeller(Usuario userSeller) {
		this.userSeller = userSeller;
	}
	public List<String> getComentarios() {
		return comentarios;
	}
	public void setComentarios(List<String> comentarios) {
		this.comentarios = comentarios;
	}
}
