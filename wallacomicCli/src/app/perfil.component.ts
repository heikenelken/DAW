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
    evento: any;
    eventoC: any;

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
        let files = this.eventoC.target.files
        if(this.eventoC != null){
          let comic = {titulo:title, autor:autor, dibujante:cartoonist, argumento:argument, foto:''};//asignar id una vez se haya creado el comic
          this.comicService.saveComic(comic).subscribe(
              comic => {
                  let comicN = comic
                  this.comicService.uploadComicImage(files, comic.id).subscribe(
                    image => console.log(image),
                    error => console.error(error)
                  );
                  window.confirm('El comic se creó correctamente')
                },
              error => console.error('error creando nuevo comic: '+ error)
          );
        }
    }

    changeConfig(name: string, email:string, facebook:string, twitter:string, password:string, description:string, image:string){
        let profile: Usuario
        let userName = name
        let userEmail = email
        let userF = facebook
        let userT = twitter
        let userPass = password
        let userDescr = description
        this.perfilService.getUser(this.id).subscribe(
          user => {
            profile = user
            if(userName === ''){
              userName = profile.nombre
            }
            if(userEmail === ''){
              userEmail = profile.correo
            }
            if(userF === ''){
              userF = profile.facebook
            }
            if(userT === ''){
              userT = profile.twitter
            }
            if(userPass === ''){
              userPass = '123456'
            }
            if(userDescr === ''){
              userDescr = profile.descripcion
            }
            let userN = {id:this.id, nombre:userName, contraseñaHash:userPass, descripcion:userDescr, correo:userEmail, facebook:userF, twitter:userT, foto:this.id, roles:["ROLE_USER","ROLE_ADMIN"] }
            this.perfilService.updateUser(userN).subscribe(
                user => {
                  if(this.evento != null){
                    this.changeProfileImage(this.evento, this.id)
                  }
                  this.reloadProfile()
                  window.confirm('El usuario se actualizó correctamente')
                  },
                error => console.error('error actualizando usuario: '+error)
            );
          },
          error => console.error(error)
        )
    }

    reloadProfile(){
      this.perfilService.getUser(this.id).subscribe(
          usuario => {
            this.usuario = usuario
            this.load = true
          },
          error => console.error(error)
      );
    }

    saveEvent(event: any){
      this.evento = event
    }

    saveEventC(event: any){
      this.eventoC = event
    }

    changeProfileImage(event: any, idUser: number | string){
      let files = event.target.files
      this.perfilService.updateImage(idUser,files).subscribe(
        image => console.log(image),
        error => console.error(error)
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
