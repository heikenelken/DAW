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

    /*loadComponent(load: boolean){
      if(load == true){
        this.ngOnInit();
      }
    }*/

    removeAd(ad: Advertisement){
      this.advertisementService.deleteAdvertisement(ad).subscribe(
        _ => this.ngOnInit(),
        error => console.error(error)
      );
    }

}
