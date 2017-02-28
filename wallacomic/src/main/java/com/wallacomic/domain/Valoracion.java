package com.wallacomic.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Valoracion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	//Atributos basicos
	@ManyToOne
	private Usuario usuario;
	private String comentario;
	private int numEstrellas;


	
	protected Valoracion(){}
	public Valoracion(Usuario usuario, String comentario, int numEstrellas){
		this.usuario=usuario;
		this.comentario=comentario;
		this.numEstrellas=numEstrellas;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
