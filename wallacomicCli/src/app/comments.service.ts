import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
//import {withObserver} from './utils';
import { Http, Response, RequestOptions, Headers } from '@angular/http';

import { Comment } from './comments.model';

import 'rxjs/Rx';

const BASIC_URL = 'https://localhost:8443/api/valoracion/';

@Injectable()
export class CommentsService {

  constructor(private http: Http){}

  getCommentsFromUser(id: number | string){
		return this.http.get(BASIC_URL + 'usuario/' + id, { withCredentials: true }).map(
			response => response.json()
		).catch(error => this.handleError(error));
	}

  getStarsAverage(id: number | string){
    return this.http.get(BASIC_URL + 'usuario/' + id + '/media', { withCredentials: true }).map(
			response => response.json()
		).catch(error => this.handleError(error));
  }

  saveComment(comment: Comment){
    const body = JSON.stringify(comment);
    const headers = new Headers({
      'Content-Type': 'application/json',
      'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true, headers });
    console.log(body);
    if (!comment.id) {
      return this.http.post(BASIC_URL, body, options).map(
        response => response.json()
      ).catch(
        error => this.handleError(error));
    }
  }

  private handleError(error: any) {
    console.error(error);
    return Observable.throw('Server error (' + error.status + '): ' + error.text());
  }

}
