import {Component, Output, EventEmitter, OnInit}   from '@angular/core';
import {/*ROUTER_DIRECTIVES, RouteParams,*/ Router} from '@angular/router';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
//import { MODAL_DIRECTIVES } from 'ng2-bs3-modal/ng2-bs3-modal';
//import {User, UserService} from './user.service';


interface Usuario {
	nombre:string;
	contr:string;
}

@Component({
    selector: 'navbar',
    //directives: [ROUTER_DIRECTIVES, MODAL_DIRECTIVES],
    templateUrl: './navbar.component.html',
    styleUrls: ['./main.component.css']
})
export class NavbarComponent {

	closeResult: string;

	constructor(private modalService: NgbModal) {}

	open(content) {
    this.modalService.open(content).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

	//en el ejemplo de la pagina ng-bootstrap no usa un metodo close en el componente
	/*close(content) {
		this.modalService.close(result).then(() => {})
	}*/

	private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return  `with: ${reason}`;
    }
  }

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
