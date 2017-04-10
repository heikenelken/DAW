package com.wallacomic.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonView;




@Entity
@Table(name = "TB_COMIC")
public class Comic {
	
	public interface MainView{}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(MainView.class)
	private long id;
	
	//Atributos basicos
	@NotNull
	@JsonView(MainView.class)
	private String titulo;
	@NotNull
	private String autor;
	@NotNull
	private String dibujante;
	@NotNull
	private String argumento;
	@NotNull
	@JsonView(MainView.class)
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
