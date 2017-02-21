package com.wallacomic;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallacomic.Comic;

@RestController
@RequestMapping("/")
public class ComicController {
	
	@Autowired
	private ComicRepository comicRepository;
	
	@PostConstruct
	public void init(){
		comicRepository.save(new Comic("The amazing Spiderman #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "ruta de la foto"));
		comicRepository.save(new Comic("Private Eye #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "ruta de la foto"));
		comicRepository.save(new Comic("Groot #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "ruta de la foto"));
		comicRepository.save(new Comic("Batman #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "ruta de la foto"));
		comicRepository.save(new Comic("Deadpool #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "ruta de la foto"));
		comicRepository.save(new Comic("IronMan #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "ruta de la foto"));
		comicRepository.save(new Comic("Barrier #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "ruta de la foto"));

	}
	@RequestMapping("home")
	public List<Comic> getComics() throws Exception {
		return comicRepository.findAll();
	}

}
