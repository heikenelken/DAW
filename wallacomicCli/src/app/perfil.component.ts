import {Component, OnInit}   from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';

import {PerfilService} from './perfil.service';
import {CommentsService} from './comments.service';
import {LoginService} from './login.service';
import {ComicService} from './comic.service';
import {AdvertisementService} from './advertisement.service';

import {Usuario} from './usuario.model';
import {Comic} from './comic.model';
import {Advertisement} from './advertisement.model';

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
    private comics: Comic[] = [];
    anuncio: Advertisement;
    private idComic: number;

    constructor(private router: Router, private activatedRoute: ActivatedRoute, private perfilService: PerfilService,
                private commentsService: CommentsService, private modalService: NgbModal, private loginService: LoginService,
                private comicService: ComicService, private adService: AdvertisementService) {}

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
      if(this.loginService.user != undefined){
        if(this.loginService.user.id == this.id){
          this.comicService.getAllComics().subscribe(
            comics => this.comics = comics,
            error => console.error(error)
          );
        }
      }
      this.anuncio = { type: false, price: 0, comment: '', user: this.usuario, comic: undefined};
    }

    createAd(){
      this.comicService.getComic(this.idComic).subscribe(
        comic => this.anuncio.comic = comic,
        error => console.error(error)
      );
      this.adService.saveAd(this.anuncio).subscribe(
        anuncio => this.ngOnInit(),
        error => console.error(error)
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
