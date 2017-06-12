import { Usuario } from './usuario.model';
import { Message } from './message.model';

export interface Conversation {
  id?: number,
  userBuyer: Usuario,
  userSeller: Usuario,
  comentarios: Message[]
}
