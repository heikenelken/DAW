package com.wallacomic.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wallacomic.domain.Comic;
import com.wallacomic.repository.ComicRepository;

@Service
public class ComicService {
	
	private static final String FOLDER_IMG = "./src/main/resources/static/img";
	private static final String FOLDER_IMG2 = "./target/classes/static/img";

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
	
	public Comic updatePicAndSave(Comic comic, MultipartFile file){
		
		String fileName= comic.getId()+".jpg";
	
		if (!file.isEmpty()) {
			try {

				File filesFolder = new File(FOLDER_IMG);
				File filesFolder2 = new File(FOLDER_IMG2);
				if (!filesFolder.exists()) {
					filesFolder.mkdirs();
				}
				if (!filesFolder2.exists()) {
					filesFolder2.mkdirs();
				}
				File uploadedFile = new File(filesFolder.getAbsolutePath(), fileName);
				File uploadedFile2 = new File(filesFolder2.getAbsolutePath(), fileName);
				file.transferTo(uploadedFile);
				file.transferTo(uploadedFile2);
			}catch(Exception e){
				//nothing here
			}
		} //end if
		Comic cModified = comicRepository.findOne(comic.getId());
		cModified.setFoto(Long.toString(comic.getId()));
		comicRepository.save(cModified);
		return cModified;
	}
	
}
