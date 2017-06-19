import {Component,Input,EventEmitter}   from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {ComicService} from './comic.service';
import {Comic} from './comic.model';

@Component({
    selector: 'comicDetail',
    templateUrl: './comicDetail.component.html'
})
export class ComicDetailComponent {

    private comic: Comic;
    comicRef: number | string;

    chargeComics: boolean;

    @Input()
    private isLogged: boolean;

    private loaded: boolean;

    constructor(private router: Router, private activatedRoute: ActivatedRoute, private service: ComicService) {
        let id = activatedRoute.snapshot.params['id'];
        this.loaded = false;
        service.getComic(id).subscribe(
            comic => {
              this.comic = comic
              this.loaded = true
            },
            error => console.error(error)
        );
        this.comicRef = id;
        this.chargeComics = true;
    }

    back() {
      window.history.back();
    }

}
