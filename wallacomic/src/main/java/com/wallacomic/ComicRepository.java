package com.wallacomic;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wallacomic.Comic;

public interface ComicRepository extends JpaRepository<Comic, Long>{
	

}
