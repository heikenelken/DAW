import {Component, OnInit} from '@angular/core';
import {NavbarComponent} from './navbar.component';
import {FooterComponent} from './footer.component';
import {IndexComponent} from './index.component';
import {SearchComponent} from './search.component';
import {ComicGridComponent} from './comicGrid.component';
import {ComicService} from './comic.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent {

  private logged: boolean;

  hiddenNavbar(hidden: boolean){
      console.log("Hidden: "+hidden);
      this.logged = hidden;
  }
}
