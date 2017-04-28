import {Comic} from './comic.model';
import {Usuario} from './usuario.model';

export interface Advertisement{
  id?: number,
  type: boolean,
  price: number,
  comment: string,
  user: Usuario,
  comic: Comic
}
