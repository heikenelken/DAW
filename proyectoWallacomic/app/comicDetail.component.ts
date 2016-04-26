import {Component}   from 'angular2/core';
import {ROUTER_DIRECTIVES, RouteParams, Router} from 'angular2/router';
import {Comic, ComicService} from './comic.service';


@Component({
    selector: 'comicDetail',
    directives: [ROUTER_DIRECTIVES],
    templateUrl: 'app/comicDetail.component.html'
})
export class ComicDetailComponent {

  comic: Comic;

    constructor(private router: Router, routeParams: RouteParams, private service: ComicService) {
        let id = routeParams.get('id');
        service.getComic(id).subscribe(
            comic => this.comic = comic,
            error => console.error(error)
        );
    }

}
