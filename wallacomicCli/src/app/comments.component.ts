import {Component, OnInit, Input, Output, EventEmitter}   from '@angular/core';
import {Router} from '@angular/router';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';

import { CommentsService } from './comments.service';
import {LoginService} from './login.service';
import {PerfilService} from './perfil.service';

import { Comment } from './comments.model';
import { Usuario } from './usuario.model';

const EMPTY_STAR = '-o';

@Component({
    selector: 'comments',
    templateUrl: './comments.component.html'
})
export class CommentsComponent {

  comments: Comment[] = [];
  closeResult: string;
  private loaded: boolean;
  private numEstrellas: number;

  @Input()
  private userId: number;

  @Input()
  private showing: boolean;

  /*@Output()
  private event = new EventEmitter<boolean>();*/

  constructor(private commentsService: CommentsService, private modalService: NgbModal, private loginService: LoginService,
              private userService: PerfilService) {

        this.loaded = false
        this.commentsService.getCommentsFromUser(this.userId).subscribe(
          comments => {
            this.comments = comments
            this.loaded = true
          },
          error => console.log(error)
        );
        this.numEstrellas = 0

              }

  ngOnInit(){
    this.loaded = false
    this.commentsService.getCommentsFromUser(this.userId).subscribe(
      comments => {
        this.comments = comments
        this.loaded = true
      },
      error => console.log(error)
    );
    this.numEstrellas = 0
  }

  createComment(comentario: string){
    let user_receive: Usuario;
    let comment: Comment;
    console.log(this.numEstrellas)
    this.userService.getUser(this.userId).subscribe(
      user => {
        user_receive = user
        if(this.numEstrellas == 1){
          comment = { user_give: this.loginService.user, user_receive: user_receive, comentario: comentario,
                      numEstrellas: this.numEstrellas, s1: '', s2: '-empty', s3: '-empty', s4: '-empty', s5: '-empty' }
        } else if(this.numEstrellas == 2){
          comment = { user_give: this.loginService.user, user_receive: user_receive, comentario: comentario,
                      numEstrellas: this.numEstrellas, s1: '', s2: '', s3: '-empty', s4: '-empty', s5: '-empty' }
        } else if(this.numEstrellas == 3){
          comment = { user_give: this.loginService.user, user_receive: user_receive, comentario: comentario,
                      numEstrellas: this.numEstrellas, s1: '', s2: '', s3: '', s4: '-empty', s5: '-empty' }
        } else if(this.numEstrellas == 4){
          comment = { user_give: this.loginService.user, user_receive: user_receive, comentario: comentario,
                      numEstrellas: this.numEstrellas, s1: '', s2: '', s3: '', s4: '', s5: '-empty' }
        } else {
          comment = { user_give: this.loginService.user, user_receive: user_receive, comentario: comentario,
                      numEstrellas: this.numEstrellas, s1: '', s2: '', s3: '', s4: '', s5: '' }
        }
        //enviar comentario
        this.commentsService.saveComment(comment).subscribe(
          comment => {
            console.log(comment)
            this.ngOnInit()
            window.confirm('¡Comentario añadido con éxito!');
            //this.event.emit(true);
          },
          error => console.log(error)
        );
        this.numEstrellas = 0;
      },
      error => console.log(error)
    );
  }

	open(modalVotar) {
    this.modalService.open(modalVotar).result.then((result) => {
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
