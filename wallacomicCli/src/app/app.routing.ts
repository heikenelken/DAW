import { Routes, RouterModule } from '@angular/router';

import { IndexComponent } from './index.component';
import { PerfilComponent } from './perfil.component';
import { ComicDetailComponent } from './comicDetail.component';
//import { MensajesComponent } from './mensajes.component';
//import { PublicarAnuncioComponent } from './publicarAnuncio.component';*/

const appRoutes = [
  {path: 'index', name: 'Index', component: IndexComponent, useAsDefault: true},
  {path: 'perfil', name: 'Perfil', component: PerfilComponent},
  {path: 'comic/:id', name: 'ComicDetail', component: ComicDetailComponent},
  //{path: 'mensajes', name: 'Mensajes', component: MensajesComponent},
  //{path: 'publicarAnuncio', name:'PublicarAnuncio', component: PublicarAnuncioComponent},
  {path: '', redirectTo: 'index', pathMatch: 'full' }
]

export const routing = RouterModule.forRoot(appRoutes);
