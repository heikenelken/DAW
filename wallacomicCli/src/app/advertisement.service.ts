import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import { Http, Response } from '@angular/http';

import 'rxjs/Rx';

const BASIC_URL = 'https://localhost:8443/api/anuncios/';

@Injectable()
export class AdvertisementService {

  constructor(private http: Http){}

  getAdsOnSaleByComic(id: number | string){
		return this.http.get(BASIC_URL + 'venta/comic/' + id).map(
			response => response.json(),
		).catch(error => Observable.throw('Error: resource not found'));
	}

  getAdsOnBuyByComic(id: number | string){
		return this.http.get(BASIC_URL + 'compra/comic/' + id).map(
			response => response.json(),
		).catch(error => Observable.throw('Error: resource not found'));
	}

}
