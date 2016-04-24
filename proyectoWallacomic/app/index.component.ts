import {Component}   from 'angular2/core';
import {ROUTER_DIRECTIVES, RouteParams, Router} from 'angular2/router';
import {SearchComponent} from './search.component';
import {ComicGridComponent} from './comicGrid.component';


@Component({
    selector: 'index',
    directives: [ROUTER_DIRECTIVES, SearchComponent, ComicGridComponent],
    templateUrl: 'app/index.component.html'
})
export class IndexComponent {

}
