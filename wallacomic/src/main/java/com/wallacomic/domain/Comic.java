package com.wallacomic.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;




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
	
	//Atributos: Lista de anuncios de compra y lista de anuncios venta
	//Anotados con @OneToMany porque un comic tiene varios anuncios
	//Anotados con cascade porque no tiene sentido vender un comic que hemos borrado(no existe)...
	//... por lo que al borrar el comic borramos tb los anuncios referidos a ese comic
	
//	@OneToMany(cascade = CascadeType.ALL)
//	private List<Anuncio> anunciosCompra = new ArrayList<>();
//	@OneToMany(cascade = CascadeType.ALL)
//	private List<Anuncio> anunciosVenta = new ArrayList<>();
	
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
/*	public List<Anuncio> getAnunciosCompra() {
		return anunciosCompra;
	}
	public void setAnunciosCompra(List<Anuncio> anunciosCompra) {
		this.anunciosCompra = anunciosCompra;
	}
	public List<Anuncio> getAnunciosVenta() {
		return anunciosVenta;
	}
	public void setAnunciosVenta(List<Anuncio> anunciosVenta) {
		this.anunciosVenta = anunciosVenta;
	}
*/

	
}
