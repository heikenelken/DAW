import {Comic} from './comic.model';
import {Usuario} from './usuario.model';

export interface Advertisement{
  id?: number,
  tipo: boolean,
  price: number,
  comment: string,
  user: Usuario,
  comic: Comic
}
