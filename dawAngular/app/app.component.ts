import {Component, OnInit} from 'angular2/core';
import {RouteConfig, ROUTER_DIRECTIVES, RouteParams, Router} from 'angular2/router';
import {CabeceraComponent} from './cabecera.component';
import {ComicGridComponent} from './comicGrid.component';
import {FooterComponent} from './footer.component';
import {PrincipalBuscadorComponent} from './principalBuscador.component';
import {MostrarComicComponent} from './mostrarComic.component';
import {Comic, ComicService}   from './comic.service';

@Component({
  selector: 'app',
    templateUrl: 'app/app.component.html',
  providers:  [ComicService],
  directives: [ROUTER_DIRECTIVES,CabeceraComponent,ComicGridComponent,FooterComponent,PrincipalBuscadorComponent,MostrarComicComponent]
})
@RouteConfig([
    {path: '/principal/...', name: 'Principal', component: PrincipalBuscadorComponent, useAsDefault: true},
    {path: '/mostrarComic/:id', name: 'MostrarComic', component: MostrarComicComponent}
])

export class AppComponent {
    private comics: Comic[] = [];

    constructor(private comicService: ComicService){}

    search(){
      this.comics = this.comicService.getComics();
    }

    hiddenTitle(hidden: boolean){
      console.log("Hidden: "+hidden)
    }
}
