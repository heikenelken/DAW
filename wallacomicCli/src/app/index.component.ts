import {Component, OnInit, Input}   from '@angular/core';
import {/*ROUTER_DIRECTIVES, RouteParams,*/ Router} from '@angular/router';
import {SearchComponent} from './search.component';
import {ComicGridComponent} from './comicGrid.component';
import {ComicService} from './comic.service';

@Component({
    selector: 'index',
    //directives: [ROUTER_DIRECTIVES, SearchComponent, ComicGridComponent],
    templateUrl: './index.component.html'
})
export class IndexComponent {

}
