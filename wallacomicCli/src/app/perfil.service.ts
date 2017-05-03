import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
//import {withObserver} from './utils';
import { Http, Response } from '@angular/http';

import 'rxjs/Rx';

const BASIC_URL = 'https://localhost:8443/api/usuarios/';

@Injectable()
export class PerfilService {

  constructor(private http: Http){}

  getUser(id: number | string){
		return this.http.get(BASIC_URL + id, { withCredentials: true }).map(
			response => response.json()
		).catch(error => this.handleError(error));
	}

  private handleError(error: any) {
    console.error(error);
    return Observable.throw('Server error (' + error.status + '): ' + error.text());
  }

}
