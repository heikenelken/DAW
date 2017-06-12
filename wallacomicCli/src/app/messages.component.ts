import {Component, OnInit}   from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {PerfilService} from './perfil.service';
import {LoginService} from './login.service';
import {MessagesService} from './messages.service';

import {Usuario} from './usuario.model';
import {Conversation} from './conversation.model';

@Component({
    selector: 'messages',
    templateUrl: './messages.component.html'
})
export class MessagesComponent {

  private userContact: Usuario;
  private conversations: Conversation[];

  constructor(private activatedRoute: ActivatedRoute, private perfilService: PerfilService, private loginService: LoginService,
              private messagesService: MessagesService) {

    let id = activatedRoute.snapshot.params['id'];
    perfilService.getUser(id).subscribe(
      user => {
          this.userContact = user
          messagesService.getLoggedUserConversations().subscribe(
            conversations => {
              this.conversations = conversations
              if(this.userContact.id == loginService.user.id){
                console.log('cargados mis mensajes, accediendo desde nav')
              }else{
                console.log('cargados mis mensajes, accediendo desde algun contacto')
              }
            },
            error => console.log(error)
          );
      },
      error => console.log(error)
    );

  }

  back() {
    window.history.back();
  }

}
