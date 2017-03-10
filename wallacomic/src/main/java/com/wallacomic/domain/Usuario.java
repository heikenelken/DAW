package com.wallacomic.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
//import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	//Atributos basicos
	private String nombre;
	private String contraseñaHash; // al loro con esto, que no se si funciona así
	private String descripcion;
	private String correo;
	private String facebook;
	private String twitter;
	private String foto;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;
	
	protected Usuario(){}
	public Usuario(String nombre, String contraseña, String descrip, String correo, String face, String twitter, String foto, String... roles){
		this.nombre=nombre;
		this.contraseñaHash=new BCryptPasswordEncoder().encode(contraseña);
		this.descripcion=descrip;
		this.correo=correo;
		this.facebook=face;
		this.twitter=twitter;
		this.foto=foto;
		this.roles = new ArrayList<>(Arrays.asList(roles));
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
	public String getContraseñaHash() {
		return contraseñaHash;
	}
	public void setContraseñaHash(String contraseña) {
		this.contraseñaHash = contraseña;
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
	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
/*	public List<Conversacion> getConversaciones() {
		return conversaciones;
	}
	public void setConversaciones(List<Conversacion> conversaciones) {
		this.conversaciones = conversaciones;
	}*/
	
}
