package com.wallacomic.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Anuncio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private boolean type;//true if its "lo compro", false if its "lo vendo"
	@JsonIgnore
	private Comic comic;//comic that belongs
	@OneToOne(mappedBy="anuncios")
	private Usuario user;//user who has published the announce
	
	protected Anuncio(){}
	
	public Anuncio(boolean tipo, Comic comic, Usuario usuario) {
		super();
		this.type = tipo;//verificar como añadir atributos comic y usuario al cargar url´s
		this.comic = comic;
		this.user = usuario;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isTipo() {
		return type;
	}

	public void setTipo(boolean tipo) {
		this.type = tipo;
	}

	public Comic getComic() {
		return comic;
	}

	public void setComic(Comic comic) {
		this.comic = comic;
	}

	public Usuario getUsuario() {
		return user;
	}

	public void setUsuario(Usuario usuario) {
		this.user = usuario;
	}

	@Override
	public String toString() {
		return "Anuncio [id=" + id + ", type=" + type + ", comic=" + comic + ", user=" + user + "]";
	}

}
