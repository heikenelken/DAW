import {Component, Output, EventEmitter} from 'angular2/core';
import {RouteConfig, ROUTER_DIRECTIVES, RouteParams, Router} from 'angular2/router';
import {NgIf} from 'angular2/common';

@Component({
  selector: 'cabecera',
  templateUrl: 'app/cabecera.component.html',
  directives: [ROUTER_DIRECTIVES]
})

export class CabeceraComponent {

  @Output()
  hidden = new EventEmitter<boolean>();

  logeado = true;
  click(){
    this.logeado = !this.logeado;
    this.hidden.next(this.logeado);
  }
}
