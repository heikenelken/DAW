import {Component, OnInit}   from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {PerfilService} from './perfil.service';
import {Usuario} from './usuario.model';

@Component({
    selector: 'perfil',
    templateUrl: './perfil.component.html'
})
export class PerfilComponent {

    private usuario: Usuario;
    private active = false;

    constructor(private router: Router, private activatedRoute: ActivatedRoute, private perfilService: PerfilService) {
      let id = activatedRoute.snapshot.params['id'];
      perfilService.getUser(id).subscribe(
          usuario => this.usuario = usuario,
          error => console.error(error)
      );
    }

    showHide(){
      this.active = ! this.active;
      console.log(this.usuario.nombre);
    }

}
