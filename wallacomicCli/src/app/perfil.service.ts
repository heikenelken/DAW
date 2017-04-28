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
		return this.http.get(BASIC_URL + id).map(
			response => response.json()
		).catch(error => Observable.throw('Error: resource not found'));
	}

}
