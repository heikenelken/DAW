import {Component, OnInit}   from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';

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
    closeResult: string;

    constructor(private router: Router, private activatedRoute: ActivatedRoute, private perfilService: PerfilService,
                private commentsService: CommentsService, private modalService: NgbModal) {}

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

    openConfig(modalConfig) {
  		this.modalService.open(modalConfig).result.then((result) => {
  			this.closeResult = `Closed with: ${result}`;
  		}, (reason) => {
  			this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
  		});
  	}

    openPublicarAnuncio(modalPublicarAnuncio) {
  		this.modalService.open(modalPublicarAnuncio).result.then((result) => {
  			this.closeResult = `Closed with: ${result}`;
  		}, (reason) => {
  			this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
  		});
  	}

    openCrearComic(modalCrearComic) {
  		this.modalService.open(modalCrearComic).result.then((result) => {
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

    showHide(){
      this.active = ! this.active;
    }

}
