import { Usuario } from './usuario.model';

export interface Message {
  id?: number,
  message: string,
  user: Usuario
}
