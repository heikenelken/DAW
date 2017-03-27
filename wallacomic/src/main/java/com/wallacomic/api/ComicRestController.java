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

import com.wallacomic.domain.Comic;
import com.wallacomic.service.ComicService;


@RestController
@RequestMapping("/api")
public class ComicRestController {

	@Autowired
	private ComicService comicService;
	
	@RequestMapping(value = "/comics", method = RequestMethod.GET)
	public Collection<Comic> getComics() {
		return comicService.findAll();
	}
	
	@RequestMapping(value = "/comic/{id}", method = RequestMethod.GET)
	public ResponseEntity<Comic> getComic(@PathVariable int id){
		
		Comic comic = comicService.findById(id);
		if (comic != null) {
			return new ResponseEntity<>(comic, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@RequestMapping(value = "/guardarComic", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Comic createComic(@RequestBody Comic comic) {

		comicService.save(comic);

		return comic;
	}
	
	//we don't update or delete comics, so we don't need put and delete methods
	
}
