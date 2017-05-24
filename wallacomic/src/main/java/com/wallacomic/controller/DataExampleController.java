package com.wallacomic.controller;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
import com.wallacomic.service.UsuarioService;
import com.wallacomic.repository.ValoracionRepository;

@Controller
public class DataExampleController {
	
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
	
	@PostConstruct
	public void init(){
		usuarioService.save(new Usuario("AdoptaUnAlien","123456", "Pequeña descripción sin sentido contando lo chupiguay que soy.", "adoptaunalien@gmail.com", "facebook/adoptaunalien", "@adoptaunalien","1","ROLE_USER","ROLE_ADMIN"));
		usuarioService.save(new Usuario("PdrSnchz","123456", "He vuelto madafakas!!!", "adoptaunpdrsnchz@gmail.com", "facebook/pdrsnchz", "@pdrsnchz","2","ROLE_USER","ROLE_ADMIN"));
		usuarioService.save(new Usuario("MarianoRajoy","123456", "Losh eshpañolesh, mucho eshpañolesh y muy eshpañolesh", "elputomariano@gmail.com", "facebook/mariano", "@yLaEuropea?","3","ROLE_USER","ROLE_ADMIN"));

		comicRepository.save(new Comic("The amazing Spiderman #001", "John Freeman", "John Freeman", "A pesar de su poderes, Parker se esfuerza por ayudar a su viuda tía May a pagar el alquiler de su casa.", "1"));
		comicRepository.save(new Comic("Private Eye #001", "Carlos González", "Carlos González", "La serie tiene lugar en 2076 tras la 'explosión de la nube', un hecho que reveló los secretos de todo el mundo.", "2"));
		comicRepository.save(new Comic("Groot #001", "Adam Smith", "Adam Smith", "Groot (también conocido como el 'Monarca del Planeta X') es un superhéroe ficticio que aparece como personaje en publicaciones de la serie Marvel Comics.", "3"));
		comicRepository.save(new Comic("Batman #001", "Michael Pérez", "Michael Pérez", "La identidad secreta de Batman es Bruce Wayne (Bruno Díaz, en algunos países de habla hispana), un empresario multimillonario y filántropo de Gotham City.", "4"));
		comicRepository.save(new Comic("Deadpool #001", "Peter West", "Peter West", "Como un mercenario mentalmente inestable y desfigurado, Deadpool apareció originalmente como un villano en una edición del cómic New Mutants, y más tarde en ediciones de X-Force.", "5"));
		comicRepository.save(new Comic("Invincible Ironman #001", "Pablo Yeah", "Pablo Yeah", "Tony Stark es un exitoso multimillonario, empresario e ingeniero, con una lujosa vida y una enorme fortuna gracias a sus inventos y a la herencia de su padre.", "6"));
		comicRepository.save(new Comic("Barrier #001", "Sergio Santos", "Sergio Santos", "Barrier es un alumno de la URJC desesperado, que lucha contra el villano Micael por aprobar la asignatura de DAW sin morir en el intento. Sus compañeros, typescript y angular2 le ayudan en su cruzada que, de momento, no tiene muy buena pinta", "7"));
		comicRepository.save(new Comic("The Incredible Hulk #001", "Stan Lee", "Jack Kirby", "Hulk es un pedazo de bicho capaz de hacerte caquita con la mirada y va rompiendo cosas por ahi cuando le viene en gana.", "8"));
		comicRepository.save(new Comic("Captain America #275", "Stan Lee", "Su tio el del pueblo", "El Capitán América viste un traje que lleva un motivo de la bandera de los Estados Unidos, y está armado con un escudo compuesto de una aleación de un metal Extraterrestre 'Adamantium' y de Vibranium que se encuentra en continente Africano.", "9"));
		comicRepository.save(new Comic("X-Men", "Stan Lee", "Jack Kirby", "La serie parte de la idea de que la evolución humana seguiría activa, encontrándose de hecho ante una encrucijada en la que nacería una nueva especie con grandes poderes y capacidades, los mutantes. Los protagonistas de X-Men son un grupo de ellos", "10"));
		comicRepository.save(new Comic("Wolverine #001", "Len Wein", "Herb Trimpe", "Wolverine, cuyo nombre de nacimiento es James Howlett (también conocido como James Logan o simplemente Logan) es un personaje ficticio, superhéroe de Marvel Comics, miembro de los X-Men. Él es el puto amo y con sus garras de Adamantium hace mucha pupita.", "11"));
		comicRepository.save(new Comic("Daredevil", "Stan Lee", "Bill Everett", "No tengo ni puta idea de que trata Daredevil y cual es su superpoder, pero se que esta cegato el pobre y reparte ostias como panes.", "12"));
		comicRepository.save(new Comic("The Punisher #003", "Lamin Gafloja", "Medasun Besito", "Bueno bueno bueno este tio se pone a repartir justicia y se queda solo el cabrón. Se dedica a castigar a la gente, de ahí su nombre (Punisher = Castigador)", "13"));
		comicRepository.save(new Comic("The amazing Spiderman #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "1"));
		comicRepository.save(new Comic("Private Eye #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "2"));
		comicRepository.save(new Comic("Groot #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "3"));
		comicRepository.save(new Comic("Batman #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "4"));
		comicRepository.save(new Comic("Deadpool #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "5"));
		comicRepository.save(new Comic("IronMan #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "6"));
		comicRepository.save(new Comic("Barrier #001", "Carlos Sevilla", "Carlos Sevilla", "El argumento es que Spiderman es el puto amo y se pasea por el mundo tirando telas de araña.", "7"));
		
		anuncioRepository.save(new Anuncio(true, 15.0, "Busco ejemplar de Spiderman en óptimas condiciones. Precio negociable", usuarioService.findById(1), comicRepository.findById(1)));
		anuncioRepository.save(new Anuncio(true, 8.0, "Ando buscando un ejemplar de Private Eye medio decente.", usuarioService.findById(2), comicRepository.findById(2)));
		anuncioRepository.save(new Anuncio(false, 50.0, "Se vende nº1 de Iron Man en perfecto estado de revista. Mejor verlo. Precio no negociable.", usuarioService.findById(2), comicRepository.findById(6)));
		anuncioRepository.save(new Anuncio(false, 20.0, "Vendo comic de Batman al que quiero como si fuera mi hijo.", usuarioService.findById(2), comicRepository.findById(4)));
		anuncioRepository.save(new Anuncio(true, 15.0, "Quiero comprar un ejemplar de Groot #001, da igual como esté. No paso de 12€", usuarioService.findById(1), comicRepository.findById(3)));
		anuncioRepository.save(new Anuncio(true, 10.0, "Me gustaría comprar mi primer comic de Deadpool, que no esté muy usado.", usuarioService.findById(1), comicRepository.findById(5)));
		anuncioRepository.save(new Anuncio(false, 500.0, "Vendo comic de SpiderMan #001 edición limitada. Abstenerse pobretones.", usuarioService.findById(2), comicRepository.findById(1)));
		anuncioRepository.save(new Anuncio(true, 100.0, "Busco comics en general. Me sobra el dinero", usuarioService.findById(1), comicRepository.findById(7)));
		anuncioRepository.save(new Anuncio(true, 4.0, "En realidad no quero comics, busco recambios para mi moto", usuarioService.findById(3), comicRepository.findById(13)));
		
		valoracionRepository.save(new Valoracion(usuarioService.findById(1), usuarioService.findById(2), "Comic muy bonito. Me ha gustado mucho.", 4));
		valoracionRepository.save(new Valoracion(usuarioService.findById(2), usuarioService.findById(1),  "Buen tío, fiable, comic en perfecto estado", 5));

		List<Mensaje> list1 = Arrays.asList(new Mensaje(usuarioService.findById(1),"Hola amigo estaba interesado en comprarte un puto comic"), new Mensaje(usuarioService.findById(2),"Hola colega, pues resulta que ya lo he vendido menuda mierda"), new Mensaje(usuarioService.findById(1),"Pues borra el anuncio pedazo de inútil"));
		List<Mensaje> list2 = Arrays.asList(new Mensaje(usuarioService.findById(1),"-----------------------------------------------"), new Mensaje(usuarioService.findById(3),"+++++++++++++++++++++++++++++++++++++++++++++++"), new Mensaje(usuarioService.findById(1),"//////////////////////////////"));

		conversacionRepository.save(new Conversacion(usuarioService.findById(1),usuarioService.findById(2), list1));
		conversacionRepository.save(new Conversacion(usuarioService.findById(1),usuarioService.findById(3), list2));
	}
}
