import { Routes, RouterModule } from '@angular/router';

import { IndexComponent } from './index.component';
import { PerfilComponent } from './perfil.component';
import { ComicDetailComponent } from './comicDetail.component';
import { MessagesComponent } from './messages.component';
//import { PublicarAnuncioComponent } from './publicarAnuncio.component';*/

const appRoutes = [
  {path: 'index', name: 'Index', component: IndexComponent, useAsDefault: true},
  {path: 'usuario/:id', name: 'Perfil', component: PerfilComponent},
  {path: 'comic/:id', name: 'ComicDetail', component: ComicDetailComponent},
  {path: 'mensajes/:id', name: 'Mensajes', component: MessagesComponent},
  {path: '', redirectTo: 'index', pathMatch: 'full' }
]

export const routing = RouterModule.forRoot(appRoutes);
