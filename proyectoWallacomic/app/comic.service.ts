import {Injectable} from 'angular2/core';
import {Observable} from 'rxjs/Observable';
import {withObserver} from './utils';

export class Comic {

  constructor(
    public id: number,
    public titulo: string,
    public autor: string,
    public dibujante: string,
    public descripcion: string
    //public foto: string
    ) {}

}

@Injectable()
export class ComicService {

  private comics = [
    new Comic(1,'The Amazing Spiderman #001','John Freeman','John Freeman','A pesar de su poderes, Parker se esfuerza por ayudar a su viuda tía May a pagar el alquiler de su casa. Parker es molestado a veces por algunos de sus colegas (especialmente la estrella de fútbol, Flash Thompson) y, como Spider-Man, engendra la ira del editor J. Jonah Jameson. Cuando pelea contra sus enemigos por primera vez, Parker se encuentra haciendo malabares en su vida personal y se le dificulta aventurarse como Spider-Man. Con el tiempo, Peter se gradúa de la preparatoria y se inscribe en la Empire State University, donde se encuentra con su mejor amigo Harry Osborn y su primer interés amoroso Gwen Stacy, y su tía May le presenta a Mary Jane Watson.'),
    new Comic(2,'Private Eye #001','Carlos González','Carlos González','La serie tiene lugar en 2076 tras la "explosión de la nube", un hecho que reveló los secretos de todo el mundo. Como resultado de esto ya no hay Internet, y la gente es excesivamente celosa respecto a su privacidad, llegando hasta el punto de aparecer en público completamente enmascarados. La historia narra la aventura de un periodista sin licencia, un "paparazzi", que se ve envuelto en un misteriosa trama.'),
    new Comic(3,'Groot #001','Adam Smith','Adam Smith','Groot (también conocido como el "Monarca del Planeta X") es un superhéroe ficticio que aparece como personaje en publicaciones de la serie Marvel Comics. Creado por Stan Lee, Jack Kirby y Dick Ayers, el personaje hizo su primera aparición en Tales t Astonish #13 (Noviembre de 1990). Un extraterrestre, criatura con forma de árbol viviente, Groot originalmente apareció como un invasor que trataba de capturar humanos para su experimentación, una biografía lejos de su caracterización final.'),
    new Comic(4,'Batman #001','Michael Pérez','Michael Pérez','La identidad secreta de Batman es Bruce Wayne (Bruno Díaz, en algunos países de habla hispana),2 3 4 un empresario multimillonario y filántropo de Gotham City. Después de ser testigo del asesinato de sus padres cuando era niño, jura vengarse y combatir la delincuencia para lo cual se somete a un riguroso entrenamiento físico y mental. Adopta el diseño de un murciélago para su vestimenta, sus utensilios de combate y sus vehículos. A diferencia de los superhéroes, no tiene superpoderes: recurre a su intelecto, así como a aplicaciones científicas y tecnológicas para crear armas y herramientas con las cuales lleva a cabo sus actividades.'),
    new Comic(5,'Deadpool #001','Peter West','Peter West','Como un mercenario mentalmente inestable y desfigurado, Deadpool apareció originalmente como un villano en una edición del cómic New Mutants, y más tarde en ediciones de X-Force. Desde entonces, el personaje ha protagonizado varias series en curso, y comparte títulos con otros personajes, como Cable. El personaje, conocido como el "Mercenario Bocazas" ("Merc with a Mouth" en inglés), es famoso por su naturaleza comunicativa y por su tendencia a romper la cuarta pared, lo cual es utilizado por los escritores para un efecto humorístico. Su mayor enemigo es Taskmaster.'),
    new Comic(6,'Invincible Ironman #001','Pablo Yeah','Pablo Yeah','Tony Stark es un exitoso multimillonario, empresario e ingeniero, con una lujosa vida y una enorme fortuna gracias a sus inventos y a la herencia de su padre, pero su vida toma un giro repentino cuando es secuestrado por un grupo terrorista y sufre una grave lesión en el pecho cuando un fragmento de metralla se clava cerca de su corazón y amenaza con matarlo, sus captores tratan de obligarlo a construir un arma de destrucción masiva para ellos. Él crea en su lugar una poderosa armadura de hierro con armas incorporadas en el que él se metería para salvar su vida y escapar de su cautiverio.'),
    new Comic(7,'The Amazing Spiderman #002','John Freeman','John Freeman','A pesar de su poderes, Parker se esfuerza por ayudar a su viuda tía May a pagar el alquiler de su casa. Parker es molestado a veces por algunos de sus colegas (especialmente la estrella de fútbol, Flash Thompson) y, como Spider-Man, engendra la ira del editor J. Jonah Jameson. Cuando pelea contra sus enemigos por primera vez, Parker se encuentra haciendo malabares en su vida personal y se le dificulta aventurarse como Spider-Man. Con el tiempo, Peter se gradúa de la preparatoria y se inscribe en la Empire State University, donde se encuentra con su mejor amigo Harry Osborn y su primer interés amoroso Gwen Stacy, y su tía May le presenta a Mary Jane Watson.'),
    new Comic(8,'Barrier #001','Sergio Santos','Sergio Santos','Barrier es un alumno de la URJC desesperado, que lucha contra el villano Micael por aprobar la asignatura de DAW sin morir en el intento. Sus compañeros, typescript y angular2 le ayudan en su cruzada que, de momento, no tiene muy buena pinta')
  ];

  getComics() {
    return withObserver(this.comics);
  }

  getComic(id: number | string) {
    let comic = this.comics.filter(h => h.id === +id)[0]
    return withObserver(new Comic(comic.id, comic.titulo, comic.autor, comic.dibujante, comic.descripcion));
  }

  removeComic(comic: Comic){
    for(let i=0; i<this.comics.length; i++){
        if(this.comics[i].id === comic.id){
          this.comics.splice(i,1);
          break;
        }
    }
    return withObserver(undefined);
  }

  saveComic(comic: Comic){
    if(comic.id){
      let oldComic = this.comics.filter(h => h.id === comic.id)[0];
      oldComic.titulo = comic.titulo;
      oldComic.autor = comic.autor;
      oldComic.dibujante = comic.dibujante;
      oldComic.descripcion = comic.descripcion;
    } else {
      comic.id = this.comics.length+1;
      this.comics.push(comic);
    }
    return withObserver(comic);
  }
}
