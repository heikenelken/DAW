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
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	//Atributos basicos
	private String nombre;
	private String descripcion;
	private String correo;
	private String facebook;
	private String twitter;
	private String foto;
	
/*	@OneToMany(cascade = CascadeType.ALL)
    private List<Anuncio> anuncios = new ArrayList<>();
	@OneToMany(cascade = CascadeType.ALL)
	private List<Conversacion> conversaciones = new ArrayList<>();
	@OneToMany(cascade = CascadeType.ALL)
	private List<Valoracion> valoraciones = new ArrayList<>();*/
	
	protected Usuario(){}
	public Usuario(String nombre, String descrip, String correo, String face, String twitter, String foto){
		this.nombre=nombre;
		this.descripcion=descrip;
		this.correo=correo;
		this.facebook=face;
		this.twitter=twitter;
		this.foto=foto;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getFacebook() {
		return facebook;
	}
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}
	public String getTwitter() {
		return twitter;
	}
	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}
	public String getFoto(){
		return this.foto;
	}
	public void setFoto(String f){
		this.foto=f;
	}
/*	public List<Anuncio> getAnuncios() {
		return anuncios;
	}
	public void setAnuncios(List<Anuncio> anuncios) {
		this.anuncios = anuncios;
	}
	public List<Conversacion> getConversaciones() {
		return conversaciones;
	}
	public void setConversaciones(List<Conversacion> conversaciones) {
		this.conversaciones = conversaciones;
	}
	public List<Valoracion> getValoraciones() {
		return valoraciones;
	}
	public void setValoraciones(List<Valoracion> valoraciones) {
		this.valoraciones = valoraciones;
	}*/

	
}
