package com.wallacomic.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
//import javax.persistence.GenerationType;
import javax.persistence.Id;




@Entity
public class Comic {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	//Atributos basicos
	private String titulo;
	private String autor;
	private String dibujante;
	private String argumento;
	private String foto;
	
	//Constructores
	protected Comic(){}
	public Comic(String titulo, String autor,String dibujante, String argumento, String foto){
		this.titulo=titulo;
		this.autor=autor;
		this.dibujante=dibujante;
		this.argumento=argumento;
		this.foto=foto;
	}

	//Getter + Setter
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getArgumento() {
		return argumento;
	}
	public void setArgumento(String argumento) {
		this.argumento = argumento;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {

		this.foto = foto;
	}
	public String getDibujante() {
		return dibujante;
	}
	public void setDibujante(String dibujante) {
		this.dibujante = dibujante;
	}
	
}
