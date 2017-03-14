﻿package com.wallacomic.controller;

import java.util.List;
import java.util.Arrays;
import java.io.File;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.wallacomic.repository.UsuarioRepository;
import com.wallacomic.repository.ValoracionRepository;
import com.wallacomic.repository.ConversacionRepository;

@Controller
public class UsuarioController {
	
	private static final String FOLDER_IMG_USER = "./src/main/resources/static/imgUsers";
	private static final String FOLDER_IMG_USER2 = "./target/classes/static/imgUsers";

	@Autowired
	private UsuarioRepository usuarioRepository;
	
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
	
	@PostConstruct
	public void init(){
		usuarioRepository.save(new Usuario("AdoptaUnAlien","123456", "Pequeña descripción sin sentido contando lo chupiguay que soy.", "adoptaunalien@gmail.com", "facebook/adoptaunalien", "@adoptaunalien","1","ROLE_USER"));
		usuarioRepository.save(new Usuario("PdrSnchz","123456", "Vendo Opel Corsa en perfecto estado", "adoptaunpdrsnchz@gmail.com", "facebook/pdrsnchz", "@pdrsnchz","2","ROLE_USER"));
		usuarioRepository.save(new Usuario("MarianoRajoy","123456", "Losh eshpañolesh, mucho eshpañolesh y muy eshpañolesh", "elputomariano@gmail.com", "facebook/mariano", "@yLaEuropea?","2","ROLE_USER"));

		comicRepository.save(new Comic("The amazing Spiderman #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "1"));
		comicRepository.save(new Comic("Private Eye #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "2"));
		comicRepository.save(new Comic("Groot #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "3"));
		comicRepository.save(new Comic("Batman #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "4"));
		comicRepository.save(new Comic("Deadpool #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "5"));
		comicRepository.save(new Comic("IronMan #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "6"));
		comicRepository.save(new Comic("Barrier #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "7"));
		comicRepository.save(new Comic("The amazing Spiderman #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "1"));
		comicRepository.save(new Comic("Private Eye #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "2"));
		comicRepository.save(new Comic("Groot #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "3"));
		comicRepository.save(new Comic("Batman #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "4"));
		comicRepository.save(new Comic("Deadpool #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "5"));
		comicRepository.save(new Comic("IronMan #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "6"));
		comicRepository.save(new Comic("Barrier #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "7"));
		comicRepository.save(new Comic("The amazing Spiderman #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "1"));
		comicRepository.save(new Comic("Private Eye #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "2"));
		comicRepository.save(new Comic("Groot #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "3"));
		comicRepository.save(new Comic("Batman #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "4"));
		comicRepository.save(new Comic("Deadpool #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "5"));
		comicRepository.save(new Comic("IronMan #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "6"));
		comicRepository.save(new Comic("Barrier #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "7"));
		comicRepository.save(new Comic("The amazing Spiderman #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "1"));
		comicRepository.save(new Comic("Private Eye #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "2"));
		comicRepository.save(new Comic("Groot #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "3"));
		comicRepository.save(new Comic("Batman #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "4"));
		comicRepository.save(new Comic("Deadpool #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "5"));
		comicRepository.save(new Comic("IronMan #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "6"));
		comicRepository.save(new Comic("Barrier #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "7"));
		
		anuncioRepository.save(new Anuncio(true, 15.0, "Busco ejemplar de Spiderman en óptimas condiciones. Precio negociable", usuarioRepository.findById(1), comicRepository.findById(1)));
		anuncioRepository.save(new Anuncio(true, 15.0, "Busco ejemplar de Spiderman en óptimas condiciones. Precio negociable", usuarioRepository.findById(2), comicRepository.findById(2)));
		anuncioRepository.save(new Anuncio(false, 15.0, "Busco ejemplar de Spiderman en óptimas condiciones. Precio negociable", usuarioRepository.findById(2), comicRepository.findById(6)));
		anuncioRepository.save(new Anuncio(false, 15.0, "Busco ejemplar de Spiderman en óptimas condiciones. Precio negociable", usuarioRepository.findById(2), comicRepository.findById(4)));
		anuncioRepository.save(new Anuncio(true, 15.0, "Busco ejemplar de Spiderman en óptimas condiciones. Precio negociable", usuarioRepository.findById(1), comicRepository.findById(3)));
		anuncioRepository.save(new Anuncio(true, 15.0, "Busco ejemplar de Spiderman en óptimas condiciones. Precio negociable", usuarioRepository.findById(1), comicRepository.findById(5)));
		anuncioRepository.save(new Anuncio(false, 15.0, "Busco ejemplar de Spiderman en óptimas condiciones. Precio negociable", usuarioRepository.findById(1), comicRepository.findById(1)));
		anuncioRepository.save(new Anuncio(true, 15.0, "Busco ejemplar de Spiderman en óptimas condiciones. Precio negociable", usuarioRepository.findById(1), comicRepository.findById(7)));
		anuncioRepository.save(new Anuncio(true, 4.0, "dddddd dddddddddddddd dddddddddddd dddddddddddddddddddd", usuarioRepository.findById(1), comicRepository.findById(1)));
		
		valoracionRepository.save(new Valoracion(usuarioRepository.findById(1), usuarioRepository.findById(2), "Comic muy bonito. Me ha gustado mucho.", 4));
		valoracionRepository.save(new Valoracion(usuarioRepository.findById(2), usuarioRepository.findById(1),  "Buen tío, fiable, comic en perfecto estado", 5));

		List<Mensaje> list1 = Arrays.asList(new Mensaje(usuarioRepository.findById(1),"Hola amigo estaba interesado en comprarte un puto comic"), new Mensaje(usuarioRepository.findById(2),"Hola colega, pues resulta que ya lo he vendido menuda mierda"), new Mensaje(usuarioRepository.findById(1),"Pues borra el anuncio pedazo de inútil"));
		List<Mensaje> list2 = Arrays.asList(new Mensaje(usuarioRepository.findById(1),"-----------------------------------------------"), new Mensaje(usuarioRepository.findById(3),"+++++++++++++++++++++++++++++++++++++++++++++++"), new Mensaje(usuarioRepository.findById(1),"//////////////////////////////"));

		conversacionRepository.save(new Conversacion(usuarioRepository.findById(1),usuarioRepository.findById(2), list1));
		conversacionRepository.save(new Conversacion(usuarioRepository.findById(1),usuarioRepository.findById(3), list2));
	}
	
	@RequestMapping("/usuario/{id}")
	public String usuario(Model model, @PathVariable int id) throws Exception {
		//obtener anuncios de un determinado usuario
		Usuario usuario= usuarioRepository.findById(id);
		model.addAttribute("usuario", usuario);
		
		model.addAttribute("adsLoCompro", anuncioRepository.findByUserAndType(usuario,true));
		model.addAttribute("adsLoVendo", anuncioRepository.findByUserAndType(usuario,false));
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
		
		if(usuarioComponent.isLoggedUser() && usuarioComponent.getLoggedUser().getId()==id){
			return "miUsuario";
		}else{
			if(usuarioComponent.isLoggedUser()){
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
		
		Usuario usuario = new Usuario(nombre, contraseña, "", correo, "", "", "");
		
		usuarioRepository.save(usuario);
		usuarioComponent.setLoggedUser(usuario);
		
	    return "usuario_guardado";
	}

	@RequestMapping("/configuracion/{id}")
	public String configuracion(Model model, @PathVariable int id, @RequestParam String nombre, @RequestParam String correo, @RequestParam String facebook,
			 @RequestParam String twitter, @RequestParam String contraseña, @RequestParam String descripcion, @RequestParam MultipartFile file)throws Exception{
		
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
		
		if(nombre!="" && contraseña!=""){
			Usuario updatedUser= new Usuario (nombre, contraseña, descripcion, correo, facebook, twitter, "", "ROLE_USER");
			updatedUser.setId(id);
			usuarioRepository.save(updatedUser);
			model.addAttribute("user", updatedUser);
			usuarioComponent.setLoggedUser(updatedUser);
			return "usuario_guardado";
		}
		Usuario usuario = usuarioRepository.findById(id);
		model.addAttribute("user", usuario);
	    return "usuario_no_guardado";
	    
	}
	
	@RequestMapping("/usuarios")
	@ResponseBody
	public List<Usuario> usuarios(Model model) throws Exception {
		
		List<Usuario> usuarios= usuarioRepository.findAll();
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
