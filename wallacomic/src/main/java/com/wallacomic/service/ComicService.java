package com.wallacomic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.wallacomic.domain.Comic;
import com.wallacomic.repository.ComicRepository;

@Service
public class ComicService {

	@Autowired
	private ComicRepository comicRepository;
	
	public List<Comic> findAll(){
		return comicRepository.findAll();
	}
	
	public Page<Comic> findAll(Pageable page){
		return comicRepository.findAll(page);
	}
	
	public Comic findById(long id){
		return comicRepository.findById(id);
	}
	
	public Comic findOne(long id){
		return comicRepository.findOne(id);
	}
	
	public void save(Comic comic){
		comicRepository.save(comic);
	}
	
}
