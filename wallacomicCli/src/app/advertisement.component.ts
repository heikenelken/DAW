import {Component, Input, OnInit}   from '@angular/core';

import {AdvertisementService} from './advertisement.service';
import {LoginService} from './login.service';

import {Advertisement} from './advertisement.model';

@Component({
    selector: 'advertisements',
    templateUrl: './advertisement.component.html'
})
export class AdvertisementComponent {

    private adsSale: Advertisement[] = [];
    private adsBuy: Advertisement[] = [];

    @Input()
    private id: number | string;

    @Input()
    private comicCom: boolean;

    @Input()
    private adsFromUser: boolean;

    @Input()
    private idUser: number | string;

    @Input()
    private isUserLoaded: boolean;

    @Input()
    private isComicLoaded: boolean;

    constructor(private advertisementService: AdvertisementService, private loginService: LoginService){}

    ngOnInit(){
      if(this.comicCom){
        this.advertisementService.getAdsOnSaleByComic(this.id).subscribe(
          adsSale => this.adsSale = adsSale,
          error => console.log(error)
        );
        this.advertisementService.getAdsOnBuyByComic(this.id).subscribe(
          adsBuy => this.adsBuy = adsBuy,
          error => console.log(error)
        );
      }
      if(this.adsFromUser){
        this.advertisementService.getAdsOnSaleByUser(this.idUser).subscribe(
          adsSale => this.adsSale = adsSale,
          error => console.log(error)
        );
        this.advertisementService.getAdsOnBuyByUser(this.idUser).subscribe(
          adsBuy => this.adsBuy = adsBuy,
          error => console.log(error)
        );
      }
    }

    removeAd(ad: Advertisement){
      const okResponse = window.confirm('¿Deseas eliminar este anuncio?');
      if(okResponse){
        this.advertisementService.deleteAdvertisement(ad).subscribe(
          _ => this.ngOnInit(),
          error => console.error(error)
        );
      }
    }

}
