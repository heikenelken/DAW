import {Component, OnInit}   from 'angular2/core';
import {ROUTER_DIRECTIVES, RouteParams, Router} from 'angular2/router';
import {Comic, ComicService} from './comic.service.ts';

@Component({
    selector: 'comicGrid',
    directives: [ROUTER_DIRECTIVES],
    templateUrl: 'app/comicGrid.component.html'
})
export class ComicGridComponent {
  comics: Comic[] = [];

      constructor(private router:Router, private service: ComicService) {}

      ngOnInit(){
        this.service.getComics().subscribe(
          comics => this.comics = comics,
          error => console.log(error)
        );
      }

}
