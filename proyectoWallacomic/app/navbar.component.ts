import {Component, Output, EventEmitter, OnInit}   from 'angular2/core';
import {ROUTER_DIRECTIVES, RouteParams, Router} from 'angular2/router';
import { MODAL_DIRECTIVES } from 'ng2-bs3-modal/ng2-bs3-modal';
//import {User, UserService} from './user.service';

interface Usuario {
	nombre:string;
	contr:string;
}

@Component({
    selector: 'navbar',
    directives: [ROUTER_DIRECTIVES, MODAL_DIRECTIVES],
    templateUrl: 'app/navbar.component.html'
    //styleUrls: ['main.component.css']
})
export class NavbarComponent {
  @Output()
  hidden = new EventEmitter<boolean>();

  logeado = false;
  clickLogin(nombre: string, pass: string){
    this.logeado = !this.logeado;
    this.hidden.next(this.logeado);
  }
  clickLogout(){
    this.logeado = !this.logeado;
    this.hidden.next(this.logeado);
  }
}
