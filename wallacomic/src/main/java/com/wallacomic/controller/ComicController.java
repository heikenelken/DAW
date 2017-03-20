package com.wallacomic.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import java.io.File;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.wallacomic.domain.Anuncio;
import com.wallacomic.domain.Comic;
import com.wallacomic.domain.Usuario;
import com.wallacomic.domain.UsuarioComponent;
import com.wallacomic.repository.AnuncioRepository;
import com.wallacomic.repository.ComicRepository;
import com.wallacomic.repository.UsuarioRepository;

@Controller
public class ComicController {
	
	private static final String FOLDER_IMG = "./src/main/resources/static/img";
	private static final String FOLDER_IMG2 = "./target/classes/static/img";

	@Autowired
	private ComicRepository comicRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioComponent usuarioComponent;
	
	@Autowired
	private AnuncioRepository anuncioRepository;
	
    private List<Comic> totalComics = new ArrayList<Comic>();
    
	@RequestMapping("/home")
	public String home(Model model, Pageable page) throws Exception {
		List<Comic> comEven = new ArrayList<Comic>();
		List<Comic> comOdd = new ArrayList<Comic>();
		List<Comic> comEvenEven = new ArrayList<Comic>(); //column 1
		List<Comic> comEvenOdd = new ArrayList<Comic>(); // column 2
		List<Comic> comOddEven = new ArrayList<Comic>(); // column 3
		List<Comic> comOddOdd = new ArrayList<Comic>(); // column 4
		boolean numeroComics;
		
		if(page.hasPrevious()){
			//resto de paginas 
			Page<Comic> listPrev = comicRepository.findAll(page);
			for(Comic c: listPrev){
				
				totalComics.add(c);
			}
			
			numeroComics = (comicRepository.findAll(page).hasNext());
		}else if(totalComics.isEmpty()){
			
			//primeros 10 comics
			numeroComics = (comicRepository.findAll().size() > 10);
			
			Page<Comic> comFirst = comicRepository.findAll(new PageRequest(0,10));
			for(Comic com: comFirst){
				totalComics.add(com);
			}
			
		}
		else{
			numeroComics = (totalComics.size() == 10);
		}
		
		for(Comic com: totalComics){
			if(com.getId() % 2 == 0){//par
				comOdd.add(com);
			}else{
				comEven.add(com);
			}
		}
		boolean añadido = true;
		for(Comic com: comEven){
			if(añadido){//par
				comEvenEven.add(com);
				añadido = false;
			}else{
				comEvenOdd.add(com);
				añadido = true;
			}
		}
		boolean añadido2 = true;
		for(Comic com: comOdd){
			if(añadido2){//par
				comOddEven.add(com);
				añadido2 = false;
			}else{
				comOddOdd.add(com);
				añadido2 = true;
			}
		}
		
		model.addAttribute("comEvenEven", comEvenEven);
		//model.addAttribute("totalComics", totalComics);
		model.addAttribute("comEvenOdd", comEvenOdd);
		model.addAttribute("comOddEven", comOddEven);
		model.addAttribute("comOddOdd", comOddOdd);
		model.addAttribute("numComics", numeroComics);
		
		int nextPage = page.getPageNumber() + 1;
		model.addAttribute("nextPage", nextPage);
		
		if(usuarioComponent.isLoggedUser() && usuarioComponent.hasUserPermissions()){
			Usuario user = usuarioComponent.getLoggedUser();
			model.addAttribute("user", user);
			return "home_autenticado";
		}else{
			return "home";
		}
	}
	
	/*@RequestMapping("/home_autenticado")
	public String home_autenticado(Model model, Pageable page) throws Exception {
		List<Comic> comEven = new ArrayList<Comic>();
		List<Comic> comOdd = new ArrayList<Comic>();
		List<Comic> comEvenEven = new ArrayList<Comic>(); //column 1
		List<Comic> comEvenOdd = new ArrayList<Comic>(); // column 2
		List<Comic> comOddEven = new ArrayList<Comic>(); // column 3
		List<Comic> comOddOdd = new ArrayList<Comic>(); // column 4
		boolean numeroComics;
		
		if(page.hasPrevious()){
			//resto de paginas 
			List<Comic> listPrev = comicRepository.findAll(page).getContent();
			for(Comic c: listPrev){
				
				totalComics.add(c);
			}
			
			numeroComics = (comicRepository.findAll(page).hasNext());
		}else{
			//primeros 10 comics
			numeroComics = (comicRepository.findAll().size() > 10);
			
			List<Comic> comFirst = comicRepository.findAll(new PageRequest(0,10)).getContent();
			for(Comic com: comFirst){
				totalComics.add(com);
			}
		}
		
		for(Comic com: totalComics){
			if(com.getId() % 2 == 0){//par
				comEven.add(com);
			}else{
				comOdd.add(com);
			}
		}
		boolean añadido = true;
		for(Comic com: comEven){
			if(añadido){//par
				comEvenEven.add(com);
				añadido = false;
			}else{
				comEvenOdd.add(com);
				añadido = true;
			}
		}
		boolean añadido2 = true;
		for(Comic com: comOdd){
			if(añadido2){//par
				comOddEven.add(com);
				añadido2 = false;
			}else{
				comOddOdd.add(com);
				añadido2 = true;
			}
		}
		
		model.addAttribute("comEvenEven", comEvenEven);
		model.addAttribute("comEvenOdd", comEvenOdd);
		model.addAttribute("comOddEven", comOddEven);
		model.addAttribute("comOddOdd", comOddOdd);
		model.addAttribute("numComics", numeroComics);
		
		int nextPage = page.getPageNumber() + 1;
		model.addAttribute("nextPage", nextPage);
		
		Usuario user = usuarioComponent.getLoggedUser();
		model.addAttribute("user", user);
		
	    return "home_autenticado";
	}*/
	
	//Este método según los apuntes deberia devolver el valor de la lista comics junto con 
	//la vista home, pero en vez de eso devuelve y renderiza el string "home", y no se porq
	// hay q preguntar en clase. SOLUCIONADO: no hay que usar las anotaciones @RestController
	//ni @ResponseBody
	/*@RequestMapping("/home2")
	public String home2(Model model) throws Exception {
	    
		List<Comic> comics= comicRepository.findAll();
		model.addAttribute("comics",comics);
	    return "home";
	}*/

	@RequestMapping("/comic/{id}")
	public String comic(Model model, @PathVariable int id) throws Exception {
		//obtener anuncios de un determinado comic
		Comic comic= comicRepository.findById(id);
		model.addAttribute("comic", comic);
		
		model.addAttribute("adsCompra", anuncioRepository.findByComicAndType(comic,true));
		model.addAttribute("adsVenta", anuncioRepository.findByComicAndType(comic,false));
		if(usuarioComponent.isLoggedUser() && usuarioComponent.hasUserPermissions()){
			Usuario user = usuarioComponent.getLoggedUser();
			model.addAttribute("user", user);
			return "comic_autenticado";
		}else{
			return "comic";
		}
	}
	
	@RequestMapping("/crearComic")
	public String crearComic(Model model)throws Exception{
		
		if(usuarioComponent.isLoggedUser() && usuarioComponent.hasAdminPermissions()){
		
			Usuario user = usuarioComponent.getLoggedUser();
			model.addAttribute("user",user);
			
		}else{
			throw new BadCredentialsException("Error de acceso");
		}
		
	    return "crearComic";
	}
	
	@RequestMapping("/guardarComic")
	public String guardarComic(Model model, @RequestParam String titulo, @RequestParam String autor,
			@RequestParam String dibujante, @RequestParam String argumento , @RequestParam MultipartFile file)throws Exception{
		
		Usuario user = usuarioComponent.getLoggedUser();
		model.addAttribute("user",user);
		
		if(usuarioComponent.isLoggedUser() && usuarioComponent.hasAdminPermissions()){
			
			Comic comic= new Comic(titulo, autor, dibujante, argumento, "");
			comicRepository.save(comic);
			//tratamiento de file
			String fileName= comic.getId()+".jpg";
			
			if (!file.isEmpty()) {
				try {

					File filesFolder = new File(FOLDER_IMG);
					File filesFolder2 = new File(FOLDER_IMG2);
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
			
		}else{
			throw new BadCredentialsException("Error de acceso");
		}
		
		return "comic_guardado";
	}
	
	@RequestMapping("/comics")
	@ResponseBody
	public List<Comic> comics() throws Exception {
		
		List<Comic> comics= comicRepository.findAll();
	    return comics;
	}
}
