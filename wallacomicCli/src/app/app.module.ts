import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule, JsonpModule } from '@angular/http';
import { routing } from './app.routing';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar.component';
import { FooterComponent } from './footer.component';
import { IndexComponent } from './index.component';
import { SearchComponent } from './search.component';
import { ComicGridComponent } from './comicGrid.component';
import { ComicDetailComponent } from './comicDetail.component';
import { PerfilComponent } from './perfil.component';
import { AdvertisementComponent } from './advertisement.component';
import { CommentsComponent } from './comments.component';

import { ComicService } from './comic.service';
import { AdvertisementService } from './advertisement.service';
import { PerfilService } from './perfil.service';
import { CommentsService } from './comments.service';
import { LoginService } from './login.service';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    IndexComponent,
    SearchComponent,
    ComicGridComponent,
    ComicDetailComponent,
    PerfilComponent,
    AdvertisementComponent,
    CommentsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    JsonpModule,
    routing,
    NgbModule.forRoot()
  ],
  providers: [
    ComicService,
    AdvertisementService,
    PerfilService,
    CommentsService,
    LoginService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
