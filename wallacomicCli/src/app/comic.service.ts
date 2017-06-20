import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import { Http, Response, Headers, RequestOptions } from '@angular/http';

import 'rxjs/Rx';

const BASIC_URL = 'https://localhost:8443/api/comics/';

@Injectable()
export class ComicService {

  constructor(private http: Http){}

  getComics(page?: String){
		return this.http.get(BASIC_URL + page, { withCredentials: true }).map(
			response => response.json().content
		).catch(error => this.handleError(error));
	}

  getAllComics(){
		return this.http.get(BASIC_URL + 'all', { withCredentials: true }).map(
			response => response.json()
		).catch(error => this.handleError(error));
	}

  getComic(id: number | string){
    return this.http.get(BASIC_URL + id, { withCredentials: true }).map(
      response => response.json()
    ).catch(error => this.handleError(error));
  }
    
  saveComic(c){
    const body = JSON.stringify(c);
    const headers = new Headers({
      'Content-Type': 'application/json',
      'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true, headers });
    // window.confirm(body);
    if (!c.id) {
      return this.http.post(BASIC_URL, body, options).map(
        response => response.json()
      ).catch(
        error => this.handleError(error));
    }
  }

  getAmountComics(){
    return this.http.get(BASIC_URL + '', { withCredentials: true }).map(
      response => response.json().totalElements
    ).catch(error => this.handleError(error));
  }

  private handleError(error: any) {
    console.error(error);
    return Observable.throw('Server error (' + error.status + '): ' + error.text());
  }

}
