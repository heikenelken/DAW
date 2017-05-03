import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
//import {withObserver} from './utils';
import { Http, Response } from '@angular/http';

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

  private handleError(error: any) {
    console.error(error);
    return Observable.throw('Server error (' + error.status + '): ' + error.text());
  }

}
