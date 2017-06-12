import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import { Http, Response, RequestOptions, Headers } from '@angular/http';

import { Conversation } from './conversation.model';

import 'rxjs/Rx';

const BASIC_URL = 'https://localhost:8443/api/conversaciones/';

@Injectable()
export class MessagesService {

  constructor(private http: Http){}

  getLoggedUserConversations(){
		return this.http.get(BASIC_URL + 'miUsuario', { withCredentials: true }).map(
			response => response.json()
		).catch(error => this.handleError(error));
	}

  saveConversation(conver: Conversation){
    const body = JSON.stringify(conver);
    const headers = new Headers({
      'Content-Type': 'application/json',
      'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true, headers });
    if (!conver.id) {
      return this.http.post(BASIC_URL, body, options).map(
        response => response.json()
      ).catch(
        error => this.handleError(error));
    }else {
      return this.http.put(BASIC_URL + 'conver.id', body, options).map(
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
