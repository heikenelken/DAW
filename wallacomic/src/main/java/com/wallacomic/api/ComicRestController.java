package com.wallacomic.api;

import java.io.File;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonView;
import com.wallacomic.domain.Comic;
import com.wallacomic.domain.Usuario;
import com.wallacomic.service.ComicService;


@RestController
@RequestMapping("/api/comics")
public class ComicRestController {

	@Autowired
	private ComicService comicService;
	
	@JsonView(Comic.MainView.class)
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Page<Comic> getComics(@RequestParam (required=false) String page) {
		if(page != null){
			int nPage =  Integer.parseInt(page);
			return comicService.findAll(new PageRequest(nPage,10));
		
		}else{
			return comicService.findAll(new PageRequest(0,10));
		}
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public Collection<Comic> getAllComics(){
		
		return comicService.findAll();
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Comic> getComic(@PathVariable int id){
		
		Comic comic = comicService.findById(id);
		if (comic != null) {
			return new ResponseEntity<>(comic, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Comic createComic(@RequestBody Comic comic) {
		
		comicService.save(comic);
		//Comic comUpdated = comicService.updatePicAndSave(comic,file);
		return comic;
	}
	
	@RequestMapping(value = "/uploadPhoto/{id}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Comic uploadPhoto(@RequestBody MultipartFile file, @PathVariable int id) {
		
		Comic comUpdated = comicService.updatePicAndSave(comicService.findById(id),file);
		return comUpdated;
	}
	
	//we don't update or delete comics, so we don't need put and delete methods
	
}
