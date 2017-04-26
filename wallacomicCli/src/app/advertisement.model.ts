import {Comic} from './comic.model';
import {User} from './user.model';

export interface Advertisement{
  id?: number,
  type: boolean,
  price: number,
  comment: string,
  user: User,
  comic: Comic
}
