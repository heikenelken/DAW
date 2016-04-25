import {Component, OnInit} from 'angular2/core';
import {RouteConfig, ROUTER_DIRECTIVES} from 'angular2/router';
import {NavbarComponent} from './navbar.component';
import {FooterComponent} from './footer.component';
import {IndexComponent} from './index.component';
import {SearchComponent} from './search.component';
import {ComicGridComponent} from './comicGrid.component';
import {PerfilComponent} from './perfil.component';
import {MensajesComponent} from './mensajes.component';
import {PublicarAnuncioComponent} from './publicarAnuncio.component';
import {ComicService} from './comic.service';
import {Alert} from 'ng2-bootstrap/ng2-bootstrap';

@Component({
  selector: 'app',
  providers: [ComicService],
  templateUrl: 'app/app.component.html',
  //styleUrls: ['main.component.css'],
  directives: [ROUTER_DIRECTIVES, Alert, NavbarComponent, FooterComponent, IndexComponent, PerfilComponent]
})
@RouteConfig([
  {path: '/index', name: 'Index', component: IndexComponent, useAsDefault: true},
  {path: '/perfil', name: 'Perfil', component: PerfilComponent},
  {path: '/mensajes', name: 'Mensajes', component: MensajesComponent},
  {path: '/publicarAnuncio', name:'PublicarAnuncio', component: PublicarAnuncioComponent}

])
export class AppComponent {
  hiddenNavbar(hidden: boolean){
      console.log("Hidden: "+hidden)
    }
 }
