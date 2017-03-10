package com.wallacomic.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Valoracion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	//Atributos basicos
	@OneToOne
	private Usuario user_give; //User who gives the score
	@OneToOne
	private Usuario user_receive; //User who receives the score
	private String comentario;
	private int numEstrellas;


	
	protected Valoracion(){}
	public Valoracion(Usuario user_give, Usuario user_receive, String comentario, int numEstrellas){
		this.user_give=user_give;
		this.user_receive=user_receive;
		this.comentario=comentario;
		this.numEstrellas=numEstrellas;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Usuario getUser_give() {
		return user_give;
	}
	public void setUser_give(Usuario user_give) {
		this.user_give = user_give;
	}
	public Usuario getUser_receive() {
		return user_receive;
	}
	public void setUser_receive(Usuario user_receive) {
		this.user_receive = user_receive;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public int getNumEstrellas() {
		return numEstrellas;
	}
	public void setNumEstrellas(int numEstrellas) {
		this.numEstrellas = numEstrellas;
	}

	
}
