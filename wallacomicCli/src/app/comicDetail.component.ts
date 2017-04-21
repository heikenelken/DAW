import {Component}   from '@angular/core';
import {/*ROUTER_DIRECTIVES, RouteParams,*/ Router, ActivatedRoute} from '@angular/router';
import {ComicService} from './comic.service';
import {Comic} from './comic.model';

@Component({
    selector: 'comicDetail',
    //directives: [ROUTER_DIRECTIVES],
    templateUrl: './comicDetail.component.html'
})
export class ComicDetailComponent {

    comic: Comic;

    constructor(private router: Router, private activatedRoute: ActivatedRoute, private service: ComicService) {
        let id = activatedRoute.snapshot.params['id'];
        service.getComic(id).subscribe(
            comic => this.comic = comic,
            error => console.error(error)
        );

    }

}
