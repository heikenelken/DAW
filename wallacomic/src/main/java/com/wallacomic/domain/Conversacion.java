package com.wallacomic.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.beans.factory.annotation.Autowired;

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
	@OneToMany(cascade=CascadeType.ALL)
	private List<Mensaje> comentarios;
	
	protected Conversacion(){}
	public Conversacion(Usuario userBuyer, Usuario userSeller, List<Mensaje> comentarios){
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
	public List<Mensaje> getComentarios() {
		return comentarios;
	}
	public void setComentarios(List<Mensaje> comentarios) {
		this.comentarios = comentarios;
	}
}
