import {Component, Output, EventEmitter, OnInit}   from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import {PerfilService} from './perfil.service';
import {LoginService} from './login.service';

import {Usuario} from './usuario.model';


@Component({
    selector: 'navbar',
    templateUrl: './navbar.component.html',
    styleUrls: ['./main.component.css']
})
export class NavbarComponent {

	closeResult: string;
	usuario: Usuario;
	private id: number | string;

  @Output()
  hidden = new EventEmitter<boolean>();

	constructor(private activatedRoute: ActivatedRoute, private perfilService: PerfilService, private modalService: NgbModal,
              private loginService: LoginService) {}


	ngOnInit(){
    this.loginService.reqIsLogged().subscribe(
      response => this.usuario = response,
      error => console.log('Error when asking isLogged, or not user logged')
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

  clickLogin(nombre: string, pass: string){
    this.loginService.logIn(nombre, pass).subscribe(
      usuario => this.usuario = usuario,
      error => alert('Invalid user or password')
    );
  }

  clickLogout(){
    this.loginService.logOut().subscribe(
      response => { },
      error => console.log('Error when trying to log out: ' + error)
    );
  }

  clickRegistrar(userName: string, pass: string, email: string){
    let newUser = {nombre: userName, contraseñaHash: pass, descripcion: '', correo: email, facebook: '', twitter: '',
                  foto: 'default', roles: ['ROLE_USER', 'ROLE_ADMIN']}
    this.perfilService.saveUser(newUser).subscribe(
      usuario => {
        this.loginService.logIn(userName, pass).subscribe(
          usuario => this.usuario = usuario,
          error => alert('Invalid user or password')
        );
      },
      error => console.log(error)
    );
  }

}
