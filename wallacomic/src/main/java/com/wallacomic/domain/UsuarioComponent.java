package com.wallacomic.domain;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;



@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UsuarioComponent {

	private Usuario user;
	
	public Usuario getLoggedUser() {
		return user;
	}

	public void setLoggedUser(Usuario user) {
		this.user = user;
	}

	public boolean isLoggedUser() {
		return this.user != null;
	}
	
	public boolean hasAdminPermissions(){
		boolean hasPerm = false;
		List<String> permissions = user.getRoles();
		for(String r: permissions){
			if(r.equals("ROLE_ADMIN")){
				hasPerm = true;
			}
		}
		return hasPerm;
	}
	
	public boolean hasUserPermissions(){
		boolean hasPerm = false;
		List<String> permissions = user.getRoles();
		for(String r: permissions){
			if(r.equals("ROLE_USER")){
				hasPerm = true;
			}
		}
		return hasPerm;
	}
}
