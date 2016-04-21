import {Component, OnInit}   from 'angular2/core';
import {RouteConfig, ROUTER_DIRECTIVES, RouteParams, Router} from 'angular2/router';
import {Comic, ComicService}   from './comic.service';

@Component({
  selector: 'comicGrid',
  templateUrl: 'app/comicGrid.component.html',
  //providers:  [ComicService],
  directives: [ROUTER_DIRECTIVES]
})

export class ComicGridComponent implements OnInit {

comics: Comic[] = [];

constructor (private router: Router, private service: ComicService){}

    ngOnInit(){
        this.service.getComics().subscribe(
            comics=> this.comics = comics,
            error => console.log(error)
        );
    }
}
