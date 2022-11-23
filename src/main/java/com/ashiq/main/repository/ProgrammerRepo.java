package com.ashiq.main.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ashiq.main.model.Programmer;

@Repository
public interface ProgrammerRepo extends JpaRepository<Programmer, Integer>{
	// method name must be a small letter followed by a capital letter
	List<Programmer> findBypLang(String pLang);

	// custom query
	@Query("from Programmer where pName = ?1")
	List<Programmer> findp(String pName);

}
