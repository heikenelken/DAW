import {Injectable} from 'angular2/core';
import {Observable} from 'rxjs/Observable';
import {withObserver} from './utils';

export class User {

  constructor(
    public name: string,
    public surname: string,
    public email: string,
    public username: string,
    public password: string
    ) {}

}

@Injectable()
export class UserService {

  private users = [
    new User('carlos', 'sevilla', 'adoptaunalien@gmail.com', 'adoptaunalien', '1234'),
    new User('pablo', 'daganzo', 'pablodaganzo@gmail.com', 'patspats', 'pats')

  getUsers() {
    return withObserver(this.users);
  }

  getUser(id: number | string) {
    let user = this.users.filter(h => h.id === +id)[0]
    return withObserver(new User(user.name, user.surname, user.email, user.username, user.password));
  }

  //removeComic(comic: Comic){
    //for(let i=0; i<this.comics.length; i++){
      //  if(this.comics[i].id === comic.id){
        //  this.comics.splice(i,1);
          //break;
        //}
    //}
    //return withObserver(undefined);
  //}

  //saveComic(comic: Comic){
    //if(comic.id){
      //let oldComic = this.comics.filter(h => h.id === comic.id)[0];
      //oldComic.titulo = comic.titulo;
      //oldComic.autor = comic.autor;
      //oldComic.dibujante = comic.dibujante;
      //oldComic.descripcion = comic.descripcion;
    //} else {
      //comic.id = this.comics.length+1;
      //this.comics.push(comic);
    //}
    //return withObserver(comic);
  //}
}
