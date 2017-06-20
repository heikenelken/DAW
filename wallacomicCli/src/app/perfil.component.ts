import {Component, OnInit, Input, EventEmitter, ViewChild}   from '@angular/core';
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

import {AdvertisementComponent} from './advertisement.component';

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
    private comics: Comic[];
    load: boolean;
    @ViewChild(AdvertisementComponent) adComponent: AdvertisementComponent;

    constructor(private router: Router, private activatedRoute: ActivatedRoute, private perfilService: PerfilService,
                private commentsService: CommentsService, private modalService: NgbModal, private loginService: LoginService,
                private comicService: ComicService, private adService: AdvertisementService) {

                  this.id = this.activatedRoute.snapshot.params['id'];
                  this.load = false;
                  perfilService.getUser(this.id).subscribe(
                      usuario => {
                        this.usuario = usuario
                        this.load = true
                      },
                      error => console.error(error)
                  );
                  this.comicsUser = true;
                  this.loadAverage();
                  comicService.getAllComics().subscribe(
                    comics => this.comics = comics,
                    error => console.error(error)
                  );
    }

    createAd(idComic: number, typeChosen: string, price: number, comment: string){
      this.comicService.getComic(idComic).subscribe(
        comic => {
          let comicChosen = comic;
          let anuncio = { tipo: false, price: price, comment: comment, user: this.loginService.user, comic: comicChosen };
          if(typeChosen === "true"){
            anuncio = { tipo: true, price: price, comment: comment, user: this.loginService.user, comic: comicChosen };
          }
          this.adService.saveAd(anuncio).subscribe(
            anuncio => {
              window.confirm('¡Anuncio creado con éxito!');
              this.adComponent.ngOnInit();
            },
            error => console.error(error)
          );
        },
        error => console.error(error)
      );
    }

    reloadAverage(event: boolean){
      if(event){
        this.stars = []
        this.loadAverage()
      }
    }

    loadAverage(){
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

    back() {
      window.history.back();
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

    sendComic(title: string, autor: string, cartoonist: string, image:string , argument:string){
        let comic = {titulo:title, autor:autor, dibujante:cartoonist, argumento:argument, foto:image};
        this.comicService.saveComic(comic).subscribe(
            comic => {window.confirm('El comic se creó correctamente')
                     },
            error => console.error('error creando nuevo comic: '+ error)
        );
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

}
