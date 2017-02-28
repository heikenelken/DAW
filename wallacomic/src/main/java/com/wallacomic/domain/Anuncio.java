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
	
	private String comment;//short description of the announce

	//para mostrar toda la info de un anuncio se ha de mostar el usuario que lo vende/compra.
	//falta meter atributo Usuario asociado al anuncio
	protected Anuncio(){}
	
	public Anuncio(boolean tipo, double precio, String comentario) {
		super();
		this.type = tipo;//verificar como añadir atributos comic y usuario al cargar url´s
		this.price = precio;
		this.comment = comentario;
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
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Anuncio [id=" + id + ", type=" + type + ", price=" + price +", comment= "+ comment +"]";
	}

	


}
