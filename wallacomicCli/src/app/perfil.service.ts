import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import { Http, Response, RequestOptions, Headers } from '@angular/http';

import { Usuario } from './usuario.model';

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

  saveUser(user: Usuario){
    const body = JSON.stringify(user);
    const headers = new Headers({
      'Content-Type': 'application/json',
      'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true, headers });
    if (!user.id) {
      return this.http.post(BASIC_URL, body, options).map(
        response => response.json()
      ).catch(
        error => this.handleError(error));
    }//poner con un else la petición put
  }

  updateUser(user){
      const body = JSON.stringify(user);
      const headers = new Headers({
        'Content-Type': 'application/json',
        'X-Requested-With': 'XMLHttpRequest'
      });
      const options = new RequestOptions({ withCredentials: true, headers });
      return this.http.put(BASIC_URL + user.id, body, options).map(
        response => response.json()
      ).catch(
        error => this.handleError(error));
  }

  updateImage(idUser: number | string, files){
    let formData = new FormData()
    for(let file of files){
      formData.append('file',file)
    }
    let headers = new Headers({})
    const options = new RequestOptions({ withCredentials: true, headers });
    headers.delete("Content-Type")
    return this.http.post(BASIC_URL + 'updatePhoto/' + idUser, formData, options).map(
      response => response.json()
    ).catch(
      error => this.handleError(error));
  }

  private handleError(error: any) {
    console.error(error);
    return Observable.throw('Server error (' + error.status + '): ' + error.text());
  }

}
