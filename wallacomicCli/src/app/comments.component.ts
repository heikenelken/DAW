import {Component, OnInit, Input}   from '@angular/core';
import {Router} from '@angular/router';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';

import { CommentsService } from './comments.service';

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

  @Input()
  private userId: number;

  @Input()
  private showing: boolean;

  constructor(private commentsService: CommentsService, private modalService: NgbModal) {}

  ngOnInit(){
    this.commentsService.getCommentsFromUser(this.userId).subscribe(
      comments => this.comments = comments,
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
