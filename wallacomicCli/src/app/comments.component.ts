import {Component, OnInit, Input, Output, EventEmitter}   from '@angular/core';
import {Router} from '@angular/router';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';

import { CommentsService } from './comments.service';
import {LoginService} from './login.service';
import {PerfilService} from './perfil.service';

import { Comment } from './comments.model';

const EMPTY_STAR = '-o';

@Component({
    selector: 'comments',
    templateUrl: './comments.component.html'
})
export class CommentsComponent {

  comments: Comment[] = [];
  closeResult: string;
  private numEstrellas: number;

  private comment: Comment;

  @Input()
  private userId: number;

  @Input()
  private showing: boolean;

  /*@Output()
  private event = new EventEmitter<boolean>();*/

  constructor(private commentsService: CommentsService, private modalService: NgbModal, private loginService: LoginService,
              private userService: PerfilService) {}

  ngOnInit(){
    this.commentsService.getCommentsFromUser(this.userId).subscribe(
      comments => this.comments = comments,
      error => console.log(error)
    );
    if(this.loginService.isLogged){
      console.log('logueado')
      this.comment = {user_give: this.loginService.user, user_receive: undefined, comentario: '', numEstrellas: 0,
                      s1: '', s2: '', s3: '', s4: '', s5: ''}
    }
  }

  createComment(){
    //meter -empty
    if(this.comment.numEstrellas == 1){
      this.comment.s2 = '-empty'
      this.comment.s3 = '-empty'
      this.comment.s4 = '-empty'
      this.comment.s5 = '-empty'
    } else if(this.comment.numEstrellas == 2){
      this.comment.s3 = '-empty'
      this.comment.s4 = '-empty'
      this.comment.s5 = '-empty'
    } else if(this.comment.numEstrellas == 3){
      this.comment.s4 = '-empty'
      this.comment.s5 = '-empty'
    } else if(this.comment.numEstrellas == 4){
      this.comment.s5 = '-empty'
    }
    this.userService.getUser(this.userId).subscribe(
      user => {
        this.comment.user_receive = user;
        //enviar comentario
        this.commentsService.saveComment(this.comment).subscribe(
          comment => {
            console.log(comment)
            this.ngOnInit()
            this.comment = {user_give: this.loginService.user, user_receive: undefined, comentario: '', numEstrellas: 0,
                            s1: '', s2: '', s3: '', s4: '', s5: ''}
            //this.event.emit(true);
          },
          error => console.log(error)
        );
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
