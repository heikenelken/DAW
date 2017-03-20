package com.wallacomic.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.boot.CommandLineRunner;

import com.wallacomic.domain.Anuncio;
import com.wallacomic.domain.Comic;
import com.wallacomic.domain.Conversacion;
import com.wallacomic.domain.Mensaje;
import com.wallacomic.domain.Usuario;
import com.wallacomic.domain.UsuarioComponent;
import com.wallacomic.domain.Valoracion;
import com.wallacomic.repository.AnuncioRepository;
import com.wallacomic.repository.ComicRepository;
import com.wallacomic.repository.ConversacionRepository;
import com.wallacomic.repository.UsuarioRepository;
import com.wallacomic.repository.ValoracionRepository;

@Controller
public class DataExampleController implements CommandLineRunner {
	
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
	
	@Override
	public void run(String... args) throws Exception{
		usuarioRepository.save(new Usuario("AdoptaUnAlien","123456", "Pequeña descripción sin sentido contando lo chupiguay que soy.", "adoptaunalien@gmail.com", "facebook/adoptaunalien", "@adoptaunalien","1","ROLE_USER","ROLE_ADMIN"));
		usuarioRepository.save(new Usuario("PdrSnchz","123456", "Vendo Opel Corsa en perfecto estado", "adoptaunpdrsnchz@gmail.com", "facebook/pdrsnchz", "@pdrsnchz","2","ROLE_USER","ROLE_ADMIN"));
		usuarioRepository.save(new Usuario("MarianoRajoy","123456", "Losh eshpañolesh, mucho eshpañolesh y muy eshpañolesh", "elputomariano@gmail.com", "facebook/mariano", "@yLaEuropea?","2","ROLE_USER","ROLE_ADMIN"));

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
}
