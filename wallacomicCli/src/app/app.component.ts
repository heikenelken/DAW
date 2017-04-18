import {Component, OnInit} from '@angular/core';
//import {RouteConfig, ROUTER_DIRECTIVES} from '@angular/router';
import {NavbarComponent} from './navbar.component';
import {FooterComponent} from './footer.component';
import {IndexComponent} from './index.component';
import {SearchComponent} from './search.component';
import {ComicGridComponent} from './comicGrid.component';
import {PerfilComponent} from './perfil.component';
import {MensajesComponent} from './mensajes.component';
import {PublicarAnuncioComponent} from './publicarAnuncio.component';
import {ComicDetailComponent} from './comicDetail.component';
import {ComicService} from './comic.service';
//import {Alert} from 'ng2-bootstrap/ng2-bootstrap';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
  //directives: [ROUTER_DIRECTIVES, Alert, NavbarComponent, FooterComponent, IndexComponent, PerfilComponent, ComicDetailComponent]
})
export class AppComponent {
  hiddenNavbar(hidden: boolean){
      console.log("Hidden: "+hidden)
  }
}
