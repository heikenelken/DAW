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
import com.wallacomic.service.ConversacionService;


@RestController
@RequestMapping("/api/conversaciones")
public class ConversacionRestController {

	@Autowired
	private ConversacionService conversacionService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Collection<Conversacion> getConversaciones() {
		return conversacionService.findAll();
	}
	
	//get the conversations of the user logged
	@RequestMapping(value = "/miUsuario", method = RequestMethod.GET)
	public Collection<Conversacion> getMyConversations(){
		
		return conversacionService.getMyConversations();
		
	}
	
	//get a conversation with an user concrete
	@RequestMapping(value = "/usuario/{id}", method = RequestMethod.GET)
	public  ResponseEntity<Conversacion> getConversationWithUser(@PathVariable int id){
		
		//return conversacionService.getMyConversations();
		Conversacion conver = conversacionService.findConversationWithAnUser(id);
		if (conver != null) {
			return new ResponseEntity<>(conver, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	//get a concrete conversation by id
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Conversacion> getConversacion(@PathVariable int id){
		
		Conversacion conver = conversacionService.findById(id);
		if (conver != null) {
			return new ResponseEntity<>(conver, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	//CUANDO SE IMPLEMENTE USUARIO SERVICE, HACER UNA PETICION POSTMAN DE ESTA URL
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Conversacion createConversation(@RequestBody Conversacion conver) {

		conversacionService.save(conver);

		return conver;
	}
	//DA UN SERVER ERROR, SUPONGO QUE NO HAGO BIEN LA PETICION
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Conversacion> updateConversation(@PathVariable int id, @RequestBody Conversacion updatedConver) {

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
