import {Component}   from '@angular/core';
import {/*ROUTER_DIRECTIVES, RouteParams,*/ Router} from '@angular/router';


@Component({
    selector: 'perfil',
    //directives: [ROUTER_DIRECTIVES],
    templateUrl: './perfil.component.html'
})
export class PerfilComponent {

    private active = false;
    private commentsTable;

    showHide(){
      this.active = ! this.active;
      this.commentsTable.display = "block";
    }
    //cambiar display en parte de comentarios css
}
