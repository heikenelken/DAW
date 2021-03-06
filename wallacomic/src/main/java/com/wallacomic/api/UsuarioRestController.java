package com.wallacomic.api;

import java.io.File;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wallacomic.domain.Anuncio;
import com.wallacomic.domain.Conversacion;
import com.wallacomic.domain.Usuario;
import com.wallacomic.domain.UsuarioComponent;
import com.wallacomic.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioRestController {
	
	private static final String FOLDER_IMG_USER_FRONT = "../wallacomicCli/src/assets/imgUsers";	
	//private static final String FOLDER_IMG_USER_FRONT = "C:/Users/yiiiisus/Desktop/Universidad/DAW/Parte 3/wallacomicCli/src/assets/imgUsers";
	private static final String FOLDER_IMG_USER_BACK = "./src/main/resources/static/imgUsers";
	private static final String FOLDER_IMG_USER_BACK2 = "./target/classes/static/imgUsers";
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Collection<Usuario> getUsuarios() {
		return usuarioService.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> getUsuario(@PathVariable int id){
		
		Usuario usu = usuarioService.findById(id);
		if (usu != null) {
			return new ResponseEntity<>(usu, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario createUser(@RequestBody Usuario user) {
		String s=user.getContraseñaHash();
		user.setContraseñaHash(new BCryptPasswordEncoder().encode(s));
		usuarioService.save(user);

		return user;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Usuario> updateUser(@PathVariable int id, @RequestBody Usuario updatedUser) {

		Usuario user = usuarioService.findById(id);
		if (user != null) {
			String s=updatedUser.getContraseñaHash();
			updatedUser.setContraseñaHash(new BCryptPasswordEncoder().encode(s));
			updatedUser.setId(id);
			usuarioService.save(updatedUser);

			return new ResponseEntity<>(updatedUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/updatePhoto/{idUser}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Usuario> updatePhoto(@RequestBody MultipartFile file, @PathVariable int idUser) {
		
		Usuario user = usuarioService.findById(idUser);
		String fileName= idUser+".jpg";
		
		if (!file.isEmpty()) {
			try {

				File filesFolderFront = new File(FOLDER_IMG_USER_FRONT);
				File filesFolderBack = new File(FOLDER_IMG_USER_BACK);
				File filesFolderBack2 = new File(FOLDER_IMG_USER_BACK2);
				
				if (!filesFolderFront.exists()) {
					filesFolderFront.mkdirs();
				}
				if (!filesFolderBack.exists()) {
					filesFolderBack.mkdirs();
				}
				if (!filesFolderBack2.exists()) {
					filesFolderBack2.mkdirs();
				}
				
				File uploadedFileFront = new File(filesFolderFront.getAbsolutePath(), fileName);
				File uploadedFileBack = new File(filesFolderBack.getAbsolutePath(), fileName);
				File uploadedFileBack2 = new File(filesFolderBack2.getAbsolutePath(), fileName);
				
				file.transferTo(uploadedFileFront);
				file.transferTo(uploadedFileBack);
				file.transferTo(uploadedFileBack2);
				return new ResponseEntity<>(user, HttpStatus.OK);
			}catch(Exception e){
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} 
		else{
			return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
		}
	}
	
	
}
