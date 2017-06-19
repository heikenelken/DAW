import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import { Http, Headers, Response, RequestOptions } from '@angular/http';

import {Advertisement} from './advertisement.model';

import 'rxjs/Rx';

const BASIC_URL = 'https://localhost:8443/api/anuncios/';

@Injectable()
export class AdvertisementService {

  constructor(private http: Http){}

  getAdsOnSaleByComic(id: number | string){
		return this.http.get(BASIC_URL + 'venta/comic/' + id, { withCredentials: true }).map(
			response => response.json(),
		).catch(error => this.handleError(error));
	}

  getAdsOnBuyByComic(id: number | string){
		return this.http.get(BASIC_URL + 'compra/comic/' + id, { withCredentials: true }).map(
			response => response.json(),
		).catch(error => this.handleError(error));
	}

  getAdsOnSaleByUser(id: number | string){
    return this.http.get(BASIC_URL + 'venta/usuario/' + id, { withCredentials: true }).map(
      response => response.json(),
    ).catch(error => this.handleError(error));
  }

  getAdsOnBuyByUser(id: number | string){
		return this.http.get(BASIC_URL + 'compra/usuario/' + id, { withCredentials: true }).map(
			response => response.json(),
		).catch(error => this.handleError(error));
	}

  saveAd(ad: Advertisement) {

    const body = JSON.stringify(ad);
    const headers = new Headers({
      'Content-Type': 'application/json',
      'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true, headers });
    if (!ad.id) {
      return this.http.post(BASIC_URL, body, options).map(
        response => response.json()
      ).catch(
        error => this.handleError(error));
    }

  }

  deleteAdvertisement(ad: Advertisement){

    const headers = new Headers({
      'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true, headers });

    return this.http.delete(BASIC_URL + ad.id, options).map(
      response => undefined,
    ).catch(error => this.handleError(error));
  }

  private handleError(error: any) {
    console.error(error);
    return Observable.throw('Server error (' + error.status + '): ' + error.text());
  }

}
