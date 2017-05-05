import {Component, OnInit}   from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {PerfilService} from './perfil.service';
import {CommentsService} from './comments.service';

import {Usuario} from './usuario.model';

@Component({
    selector: 'perfil',
    templateUrl: './perfil.component.html'
})
export class PerfilComponent {

    usuario: Usuario;
    averageCom: number;
    private active = false;
    private comicsUser: boolean;
    private id: number | string;
    private stars: string[] = [];

    constructor(private router: Router, private activatedRoute: ActivatedRoute, private perfilService: PerfilService,
                private commentsService: CommentsService) {}

    ngOnInit(){
      this.id = this.activatedRoute.snapshot.params['id'];
      this.perfilService.getUser(this.id).subscribe(
          usuario => this.usuario = usuario,
          error => console.error(error)
      );
      this.comicsUser = true;
      this.commentsService.getStarsAverage(this.id).subscribe(
        averageCom => {
          this.averageCom = averageCom;
          for(let i=0; i < this.averageCom; i++){
            this.stars.push('');
          }
          for(let j=this.averageCom; j < 5; j++){
            this.stars.push('-o');
          }
        },
        error => console.log(error)
      );

    }

    showHide(){
      this.active = ! this.active;
    }

}
