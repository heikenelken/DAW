import {Component, OnInit}   from '@angular/core';
import {/*ROUTER_DIRECTIVES, RouteParams,*/ Router} from '@angular/router';
import {ComicService} from './comic.service';
import {Comic} from './comic.model';

@Component({
    selector: 'comicGrid',
    //directives: [ROUTER_DIRECTIVES],
    templateUrl: './comicGrid.component.html'
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
