import {Component, Output, EventEmitter, OnInit}   from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import {PerfilService} from './perfil.service';

import {Usuario} from './usuario.model';


@Component({
    selector: 'navbar',
    //directives: [ROUTER_DIRECTIVES, MODAL_DIRECTIVES],
    templateUrl: './navbar.component.html',
    styleUrls: ['./main.component.css']
})
export class NavbarComponent {

	closeResult: string;
	usuario: Usuario;
	private id: number | string;

	constructor(private activatedRoute: ActivatedRoute, private perfilService: PerfilService, private modalService: NgbModal) {}

	ngOnInit(){
		this.id = this.activatedRoute.snapshot.params['id'];
		this.perfilService.getUser(this.id).subscribe(
				usuario => this.usuario = usuario,
				error => console.error(error)
		);
	}

	openRegistro(modalRegistro) {
    this.modalService.open(modalRegistro).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

	openAcceso(modalAcceso) {
		this.modalService.open(modalAcceso).result.then((result) => {
			this.closeResult = `Closed with: ${result}`;
		}, (reason) => {
			this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
		});
	}


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

  logeado = true;
  clickLogin(nombre: string, pass: string){
    this.logeado = !this.logeado;
    this.hidden.next(this.logeado);
  }
  clickLogout(){
    this.logeado = !this.logeado;
    this.hidden.next(this.logeado);
  }
}
