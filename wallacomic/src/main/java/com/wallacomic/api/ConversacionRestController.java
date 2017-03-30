package com.wallacomic.api;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wallacomic.domain.Conversacion;
import com.wallacomic.domain.UsuarioComponent;
import com.wallacomic.service.ConversacionService;


@RestController
@RequestMapping("/api")
public class ConversacionRestController {

	@Autowired
	private ConversacionService conversacionService;
	
	@Autowired
	private UsuarioComponent usuarioComponent;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(value = "/conversaciones/", method = RequestMethod.GET)
	public Collection<Conversacion> getConversaciones() {
		return conversacionService.findAll();
	}
	//get the conversations of the user logged
	@RequestMapping(value = "/conversaciones/miUsuario", method = RequestMethod.GET)
	public Collection<Conversacion> getMyConversations(){
		
		return conversacionService.findByUserBuyerOrUserSeller(usuarioComponent.getLoggedUser(),
				usuarioComponent.getLoggedUser());
		
	}
	//get a conversation with an user concrete
	@RequestMapping(value = "/conversacion/usuario/{id}", method = RequestMethod.GET)
	public Collection<Conversacion> getConversationConcrete(@PathVariable int id){
		//mismo metodo que en controller
		return conversacionService.findByUserSellerAndUserBuyer(usuarioService.findById(id),usuarioComponent.getLoggedUser());
		
	}
	
	@RequestMapping(value = "/conversaciones/{id}", method = RequestMethod.GET)
	public ResponseEntity<Conversacion> getConversacion(@PathVariable int id){
		
		Conversacion conver = conversacionService.findById(id);
		if (conver != null) {
			return new ResponseEntity<>(conver, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}

	@RequestMapping(value = "/conversaciones/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Conversacion createConversation(@RequestBody Conversacion conver) {

		conversacionService.save(conver);

		return conver;
	}
	
	@RequestMapping(value = "/conversaciones/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Conversacion> updateBook(@PathVariable int id, @RequestBody Conversacion updatedConver) {

		Conversacion conver = conversacionService.findById(id);
		if (conver != null) {

			updatedConver.setId(id);
			conversacionService.save(updatedConver);

			return new ResponseEntity<>(updatedConver, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	//we don't delete conversations
	
}
