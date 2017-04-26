import {Component}   from '@angular/core';
import {/*ROUTER_DIRECTIVES, RouteParams,*/ Router} from '@angular/router';


@Component({
    selector: 'perfil',
    //directives: [ROUTER_DIRECTIVES],
    templateUrl: './perfil.component.html'
})
export class PerfilComponent {

    private active = false;

    showHide(){
      this.active = ! this.active;
    }

}
