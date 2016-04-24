import {Component} from 'angular2/core';
import {RouteConfig, ROUTER_DIRECTIVES} from 'angular2/router';
import {NavbarComponent} from './navbar.component';
import {FooterComponent} from './footer.component';
import {IndexComponent} from './index.component';
import {SearchComponent} from './search.component';
import {ComicGridComponent} from './comicGrid.component';
import {PerfilComponent} from './perfil.component';
import {Alert} from 'ng2-bootstrap/ng2-bootstrap';

@Component({
  selector: 'app',
  templateUrl: 'app/app.component.html',
  directives: [ROUTER_DIRECTIVES, Alert, NavbarComponent, FooterComponent, IndexComponent, PerfilComponent]
})
@RouteConfig([
  {path: '/index', name: 'Index', component: IndexComponent, useAsDefault: true},
  {path: '/perfil', name: 'Perfil', component: PerfilComponent}

])
export class AppComponent {
  hiddenNavbar(hidden: boolean){
      console.log("Hidden: "+hidden)
    }
 }
