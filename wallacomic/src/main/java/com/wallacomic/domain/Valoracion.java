package com.wallacomic.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Valoracion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	//Atributos basicos
	@OneToOne
	private Usuario user_give; //User who gives the score
	@OneToOne
	private Usuario userReceive; //User who receives the score
	private String comentario;
	private int numEstrellas;
	private String s1;
	private String s2;
	private String s3;
	private String s4;
	private String s5;
	
	protected Valoracion(){}
	public Valoracion(Usuario user_give, Usuario user_receive, String comentario, int numEstrellas){
		this.user_give=user_give;
		this.userReceive=user_receive;
		this.comentario=comentario;
		this.numEstrellas=numEstrellas;
		this.s1 = "";
		this.s2 = "";
		this.s3 = "";
		this.s4 = "";
		this.s5 = "";
		
		String [] estrellas = new String[5];
		String [] str1 = emptyOrNot(estrellas);
		for (int i=0; i < str1.length; i++){
			switch(i){
				case 0:
					this.setS1(str1[i]);
				break;
				case 1:
					this.setS2(str1[i]);
				break;
				case 2:
					this.setS3(str1[i]);
				break;
				case 3:
					this.setS4(str1[i]);
				break;
				case 4:
					this.setS5(str1[i]);
				break;
			}
		}
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Usuario getUser_give() {
		return user_give;
	}
	public void setUser_give(Usuario user_give) {
		this.user_give = user_give;
	}
	public Usuario getUser_receive() {
		return userReceive;
	}
	public void setUser_receive(Usuario user_receive) {
		this.userReceive = user_receive;
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
	public String getS1() {
		return s1;
	}
	public void setS1(String s1) {
		this.s1 = s1;
	}
	public String getS2() {
		return s2;
	}
	public void setS2(String s2) {
		this.s2 = s2;
	}
	public String getS3() {
		return s3;
	}
	public void setS3(String s3) {
		this.s3 = s3;
	}
	public String getS4() {
		return s4;
	}
	public void setS4(String s4) {
		this.s4 = s4;
	}
	public String getS5() {
		return s5;
	}
	public void setS5(String s5) {
		this.s5 = s5;
	}
	
	public String [] emptyOrNot(String[] st){
		
		for(int i=0; i < st.length; i++){
			if(this.getNumEstrellas()-1 >= i){
				st[i] = "";
			}else{
				st[i] = "-empty";
			}
		}
		return st;
	}
	
}
