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

import com.wallacomic.domain.Anuncio;
import com.wallacomic.domain.Comic;
import com.wallacomic.domain.UsuarioComponent;
import com.wallacomic.service.AnuncioService;
import com.wallacomic.service.ComicService;


@RestController
@RequestMapping("/api/anuncios")
public class AnuncioRestController {

	@Autowired
	private AnuncioService anuncioService;
	
	@Autowired
	private ComicService comicService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Collection<Anuncio> getAnuncios() {
		return anuncioService.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Anuncio> getAnuncio(@PathVariable int id){
		
		Anuncio ad = anuncioService.findById(id);
		if (ad != null) {
			return new ResponseEntity<>(ad, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	 //falta añadir getAnuncios de un tipo determinado de cierto usuario pasado por parámetro
	
	@RequestMapping(value = "/venta/comic/{id}", method = RequestMethod.GET)
	public Collection<Anuncio> getAdvertisementsOnSaleByComic(@PathVariable int id){
		
		return anuncioService.findByComicAndType(comicService.findById(id), false);
		
	}
	
	@RequestMapping(value = "/compra/comic/{id}", method = RequestMethod.GET)
	public Collection<Anuncio> getAdvertisementsOnBuyByComic(@PathVariable int id){
		
		return anuncioService.findByComicAndType(comicService.findById(id), true);
		
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Anuncio createAdvertisement(@RequestBody Anuncio ad) {

		anuncioService.save(ad);

		return ad;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Anuncio> deleteAdvertisement(@PathVariable long id) {
		
		if(anuncioService.existAd(id)){
			anuncioService.delete(id);
			return new ResponseEntity <>(null, HttpStatus.OK);
		}else{
			return new ResponseEntity <>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	//we don't update advertisements
	
}
