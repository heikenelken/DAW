import {Component, OnInit}   from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {PerfilService} from './perfil.service';
import {Usuario} from './usuario.model';

@Component({
    selector: 'perfil',
    templateUrl: './perfil.component.html'
})
export class PerfilComponent {

    usuario: Usuario;
    private active = false;
    private comicsUser: boolean;
    private id: number | string;

    constructor(private router: Router, private activatedRoute: ActivatedRoute, private perfilService: PerfilService) {
      this.id = activatedRoute.snapshot.params['id'];
      perfilService.getUser(this.id).subscribe(
          usuario => this.usuario = usuario,
          error => console.error(error)
      );
      this.comicsUser = true;
    }

    showHide(){
      this.active = ! this.active;
      console.log(this.usuario.nombre);
    }

}
