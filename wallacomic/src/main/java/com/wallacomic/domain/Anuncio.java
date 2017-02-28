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
	
	private double price;//price of the announce

	protected Anuncio(){}
	
	public Anuncio(boolean tipo, double precio) {
		super();
		this.type = tipo;//verificar como añadir atributos comic y usuario al cargar url´s
		this.price = precio;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Anuncio [id=" + id + ", type=" + type + ", price=" + price +"]";
	}


}
