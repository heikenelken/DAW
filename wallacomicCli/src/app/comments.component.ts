import {Component, OnInit, Input}   from '@angular/core';
import {Router} from '@angular/router';

import { CommentsService } from './comments.service';

import { Comment } from './comments.model';

const EMPTY_STAR = '-empty';

@Component({
    selector: 'comments',
    templateUrl: './comments.component.html'
})
export class CommentsComponent {

  comments: Comment[] = [];

  @Input()
  private userId: number;

  @Input()
  private showing: boolean;

  constructor(private commentsService: CommentsService) {}

  ngOnInit(){
    this.commentsService.getCommentsFromUser(this.userId).subscribe(
      comments => this.comments = comments,
      error => console.log(error)
    );
    for(let com of this.comments){
      if(com.s1 === '-empty'){
        com.s1 = EMPTY_STAR;
      }
      if(com.s2 === '-empty'){
        com.s2 = EMPTY_STAR;
      }
      if(com.s3 === '-empty'){
        com.s3 = EMPTY_STAR;
      }
      if(com.s4 === '-empty'){
        com.s4 = EMPTY_STAR;
      }
      if(com.s5.toString() === '-empty'){
        com.s5 = '-o';
      }
    }
  }

}
