import {Component} from 'angular2/core';
import {RouteConfig, ROUTER_DIRECTIVES, RouteParams, Router} from 'angular2/router';
import {Comic, ComicService}   from './comic.service';

@Component({
  selector: 'mostrarComic',
  templateUrl: 'app/mostrarComic.component.html',
//providers:  [BookService],
  directives: [ROUTER_DIRECTIVES]
})

export class MostrarComicComponent { 
    
    comic: Comic;
    
    //servicio observable
    constructor(private router: Router, routeParams: RouteParams, private service: ComicService){
        let id= routeParams.get('id');
        service.getComic(id).subscribe(
            comic=> this.comic = comic,
            error=> console.error(error)
        );
    }

}