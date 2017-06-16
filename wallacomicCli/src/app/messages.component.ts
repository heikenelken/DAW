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
  private actualConversation: Conversation;

  constructor(private activatedRoute: ActivatedRoute, private perfilService: PerfilService, private loginService: LoginService,
              private messagesService: MessagesService) {

    let id = activatedRoute.snapshot.params['id'];
    let created = false;
    perfilService.getUser(id).subscribe(
      user => {
          this.userContact = user
          messagesService.getLoggedUserConversations().subscribe(
            conversations => {
              this.conversations = conversations
              if(this.userContact.id != loginService.user.id){
                for(let conver of this.conversations){
                  if((this.userContact.id == conver.userBuyer.id) || (this.userContact.id == conver.userSeller.id)){
                    created = true
                  }
                }
                if(!created){
                  let newConver = { userBuyer: loginService.user, userSeller: this.userContact, comentarios: [] }
                  messagesService.saveConversation(newConver).subscribe(
                    conversation => this.ngOnInit(),
                    error => console.log(error)
                  );
                }
              }
            },
            error => console.log(error)
          );
      },
      error => console.log(error)
    );

  }

  ngOnInit(){
    this.conversations = []
    this.messagesService.getLoggedUserConversations().subscribe(
      conversations => {
        this.conversations = conversations
        console.log(this.conversations)
      },
      error => console.log(error)
    );
  }

  back() {
    window.history.back();
  }

  openConversation(idUser: number){
    this.messagesService.getSelectedConversation(idUser).subscribe(
      conversation => this.actualConversation = conversation,
      error => console.log(error)
    );
  }

  sendMessage(message: string){
    this.actualConversation.comentarios.push({message: message, user: this.loginService.user})
    this.messagesService.saveConversation(this.actualConversation).subscribe(
      conversation => this.ngOnInit(),
      error => console.log(error)
    );
  }

}
