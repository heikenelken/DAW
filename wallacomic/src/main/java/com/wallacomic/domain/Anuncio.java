package com.wallacomic.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Anuncio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private boolean type;//true if its "lo compro", false if its "lo vendo"

	protected Anuncio(){}
	
	public Anuncio(boolean tipo) {
		super();
		this.type = tipo;//verificar como añadir atributos comic y usuario al cargar url´s
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



	@Override
	public String toString() {
		return "Anuncio [id=" + id + ", type=" + type + "]";
	}

}
