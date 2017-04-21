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
  private actualPage = 0;

      constructor(private router:Router, private service: ComicService) {}

      ngOnInit(){
        this.service.getComics('').subscribe(
          comics => this.comics = comics,
          error => console.log(error)
        );
      }

      loadMoreComics(){
        this.actualPage += 1;
        this.service.getComics('?page='+ this.actualPage +'&size=10').subscribe(
          comics => {
            let moreComics = comics;
            this.comics.push(moreComics);
            console.log(moreComics);
          },
          error => console.log(error)
        );
      }

}
