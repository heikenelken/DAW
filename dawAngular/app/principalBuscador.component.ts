import {Component} from 'angular2/core';
import {RouteConfig, ROUTER_DIRECTIVES, RouteParams, Router} from 'angular2/router';
import {ComicGridComponent} from './comicGrid.component';

@Component({
  selector: 'principalBuscador',
  templateUrl: 'app/principalBuscador.component.html',
//providers:  [BookService],
  directives: [ROUTER_DIRECTIVES,ComicGridComponent]
})
@RouteConfig([
    {path: '/comicGrid', name: 'ComicGrid', component: ComicGridComponent, useAsDefault: true}
  
])
export class PrincipalBuscadorComponent { }
