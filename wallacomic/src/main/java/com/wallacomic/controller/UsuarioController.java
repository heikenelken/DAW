package com.wallacomic.controller;

import java.util.List;
import java.util.Arrays;
import java.io.File;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wallacomic.domain.Anuncio;
import com.wallacomic.domain.Conversacion;
import com.wallacomic.domain.Mensaje;
import com.wallacomic.domain.Comic;
import com.wallacomic.domain.Usuario;
import com.wallacomic.domain.UsuarioComponent;
import com.wallacomic.domain.Valoracion;
import com.wallacomic.repository.AnuncioRepository;
import com.wallacomic.repository.ComicRepository;
import com.wallacomic.service.UsuarioService;
import com.wallacomic.repository.ValoracionRepository;
import com.wallacomic.service.AnuncioService;
import com.wallacomic.service.UsuarioService;
import com.wallacomic.repository.ConversacionRepository;

@Controller
public class UsuarioController {
	
	private static final String FOLDER_IMG_USER = "./src/main/resources/static/imgUsers";
	private static final String FOLDER_IMG_USER2 = "./target/classes/static/imgUsers";


	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioComponent usuarioComponent;
	
	@Autowired
	private ConversacionRepository conversacionRepository;

	@Autowired
	private ComicRepository comicRepository;

	@Autowired
	private AnuncioRepository anuncioRepository;
	
	@Autowired
	private ValoracionRepository valoracionRepository;
	
	@Autowired
	private AnuncioService anuncioService;
	
	@RequestMapping("/usuario/{id}")
	public String usuario(Model model, @PathVariable int id) throws Exception {
		//obtener anuncios de un determinado usuario
		Usuario usuario= usuarioService.findById(id);
		model.addAttribute("usuario", usuario);
		
		//model.addAttribute("adsLoCompro", anuncioRepository.findByUserAndType(usuario,true));
		//model.addAttribute("adsLoVendo", anuncioRepository.findByUserAndType(usuario,false));
		model.addAttribute("adsLoCompro", anuncioService.findByUserAndType(usuario,true));
		model.addAttribute("adsLoVendo", anuncioService.findByUserAndType(usuario,false));
		//cargar valoraciones del usuario
		model.addAttribute("comments", valoracionRepository.findByuserReceive(usuario));
		//extraer valoracion media del usuario
		int totalValuations = 0;
		int cont = 0;
		List<Valoracion> valuations = valoracionRepository.findByuserReceive(usuario);
		if(!valuations.isEmpty()){
			for(Valoracion v: valuations){
				totalValuations += v.getNumEstrellas();
				cont += 1;
			}
		}else{
			cont = 1;
		}
		
		int averageValuation = totalValuations / cont;
		
		String [] averageStars = new String[5];
		for(int i=0; i < averageStars.length; i++){
			if(averageValuation-1 >= i){
				averageStars[i] = "";
			}else{
				averageStars[i] = "-empty";
			}
		}
		
		model.addAttribute("avS1", averageStars[0]);
		model.addAttribute("avS2", averageStars[1]);
		model.addAttribute("avS3", averageStars[2]);
		model.addAttribute("avS4", averageStars[3]);
		model.addAttribute("avS5", averageStars[4]);
		
		if(usuarioComponent.isLoggedUser() && usuarioComponent.getLoggedUser().getId()==id && usuarioComponent.hasAdminPermissions()){
			return "miUsuario";
		}else{
			if(usuarioComponent.isLoggedUser() && usuarioComponent.getLoggedUser().getId()!=id && usuarioComponent.hasUserPermissions()){
				Usuario user = usuarioComponent.getLoggedUser();
				model.addAttribute("user", user);
				return "usuario_autenticado";
			}else{
				return "usuario";
			}
		}
	    
	}
	
	@RequestMapping("/guardarUsuario")
	public String guardarUsuario(Model model, @RequestParam String nombre, @RequestParam String contraseña,
			@RequestParam String correo)throws Exception{
		
		Usuario usuario = new Usuario(nombre, contraseña, " ", correo, " ", " ", " ","USER","ADMIN");
		
		usuarioService.save(usuario);
		usuarioComponent.setLoggedUser(usuario);
		
	    return "usuario_guardado";
	}

	@RequestMapping("/configuracion/{id}")
	public String configuracion(Model model, @PathVariable long id, @RequestParam String nombre, @RequestParam String correo, @RequestParam String facebook,
			 @RequestParam String twitter, @RequestParam String contraseña, @RequestParam String descripcion, @RequestParam MultipartFile file)throws Exception{
		
		if(usuarioComponent.isLoggedUser() && usuarioComponent.getLoggedUser().getId()==id && usuarioComponent.hasAdminPermissions()){
			//tratamiento de file
			String fileName= id+".jpg";
			
			if (!file.isEmpty()) {
				try {

					File filesFolder = new File(FOLDER_IMG_USER);
					File filesFolder2 = new File(FOLDER_IMG_USER2);
					if (!filesFolder.exists()) {
						filesFolder.mkdirs();
					}
					if (!filesFolder2.exists()) {
						filesFolder2.mkdirs();
					}
					File uploadedFile = new File(filesFolder.getAbsolutePath(), fileName);
					File uploadedFile2 = new File(filesFolder2.getAbsolutePath(), fileName);
					file.transferTo(uploadedFile);
					file.transferTo(uploadedFile2);
				}catch(Exception e){
					//nothing here
				}
			} //end if
			
			if(!nombre.equals("") && !contraseña.equals("")){
				Usuario updatedUser= new Usuario (nombre, contraseña, descripcion, correo, facebook, twitter, Long.toString(id), "ROLE_USER", "ROLE_ADMIN");
				updatedUser.setId(id);
				usuarioService.save(updatedUser);
				model.addAttribute("user", updatedUser);
				usuarioComponent.setLoggedUser(updatedUser);
				return "usuario_guardado";
			}
		}else{
			throw new BadCredentialsException("Error de modificacion de parametros");
		}
		
		Usuario usuario = usuarioService.findById(id);
		model.addAttribute("user", usuario);
	    return "usuario_no_guardado";
	    
	}
	
	@RequestMapping("/usuarios")
	@ResponseBody
	public List<Usuario> usuarios(Model model) throws Exception {
		
		List<Usuario> usuarios= usuarioService.findAll();
		model.addAttribute("usuarios", usuarios);
		
	    return usuarios;
	}
	
	/*@RequestMapping("/login")
    public String login() {
    	return "login";
    }*/
	
	@RequestMapping("/loginerror")
    public String loginerror() {
    	return "loginerror";
    }
	
}
