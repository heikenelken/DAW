import {Component, OnInit}   from '@angular/core';
import {Router} from '@angular/router';
import {ComicService} from './comic.service';
import {Comic} from './comic.model';

@Component({
    selector: 'comicGrid',
    templateUrl: './comicGrid.component.html'
})
export class ComicGridComponent {
  private comics: Comic[] = [];
  private actualPage = 0;
  private nComics = 0;
  private loadMore = true;
  comEven: Comic[] = [];
  comOdd: Comic[] = [];
  comEvenEven: Comic[] = [];
  comEvenOdd: Comic[] = [];
  comOddOdd: Comic[] = [];
  comOddEven: Comic[] = [];

      constructor(private router:Router, private service: ComicService) {}

      ngOnInit(){
        this.service.getComics('').subscribe(
          comics => {
              this.comics = comics;
              this.sortInColumns(this.comics);
          },
          error => console.log(error)
        );
        this.service.getAmountComics().subscribe(
          nComics => this.nComics = nComics,
          error => console.error(error)
        );
      }

      loadMoreComics(){
        this.actualPage += 1;
        this.service.getComics('?page='+ this.actualPage +'&size=10').subscribe(
          comics => {
            if(this.comics.length < this.nComics){
              let moreComics = comics;
              this.sortInColumns(moreComics);
              for(let comic of moreComics){
                  this.comics.push(comic);
              }

              if(this.comics.length == this.nComics){
                this.loadMore = false;
              }
            }
          },
          error => console.log(error)
        );

      }

      sortInColumns(moreComics: Comic[]){

        let added1 = true;
        let added2 = true;
        for(let c of moreComics){
          if(moreComics.indexOf(c) % 2 == 0){
            this.comEven.push(c);
          }else{
            this.comOdd.push(c);
          }
        }

        for(let comicE of this.comEven){
            if(added1){
              this.comEvenEven.push(comicE);
              added1 = false;
            }else{
              this.comEvenOdd.push(comicE);
              added1 = true;
            }
        }

        for(let comicO of this.comOdd){
            if(added2){
              this.comOddEven.push(comicO);
              added2 = false;
            }else{
              this.comOddOdd.push(comicO);
              added2 = true;
            }
        }

        this.comEven.splice(0,this.comEven.length);
        this.comOdd.splice(0,this.comEven.length);
        this.comEven.length = 0;
        this.comOdd.length = 0;
      }

}
