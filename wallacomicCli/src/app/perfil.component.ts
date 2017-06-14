import {Component, OnInit, Input, EventEmitter}   from '@angular/core';
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
    private comics: Comic[];
  //  private comicChosen : Comic;
  /*  @Input()
    private reloadAds = new EventEmitter<boolean>();*/
    load: boolean;

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
                  commentsService.getStarsAverage(this.id).subscribe(
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
                  comicService.getAllComics().subscribe(
                    comics => this.comics = comics,
                    error => console.error(error)
                  );
    }

    /*ngOnInit(){
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
      console.log('logueado: ' + this.loginService.isLogged)
      //console.log("Checking condicion carga de comics")
      if(this.loginService.isLogged){
        console.log("Hay user logueado")
        if(this.loginService.user.id == this.id){
          console.log("mismo id")
          this.comicService.getAllComics().subscribe(
            comics => this.comics = comics,
            error => console.error(error)
          );
        }
      }
    }*/

  /*  reloadProfile(event: boolean){
      if(event){
          this.ngOnInit()
      }
    }*/

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
              console.log(anuncio);
              window.confirm('Anuncio creado con éxito (Recarga la página para ver el resultado)');
              //this.load = true;//para recargar los anuncios lanzamos un evento al componente Advertisement
              //this.reloadAds.emit(this.load);
            },
            error => console.error(error)
          );
        },
        error => console.error(error)
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
