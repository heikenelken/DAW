import {Usuario} from './usuario.model';

export interface Comment {
  id?: number;
  user_give: Usuario;
  user_receive: Usuario;
  comentario: string;
  numEstrellas: number;
  s1: string;
  s2: string;
  s3: string;
  s4: string;
  s5: string;
}
