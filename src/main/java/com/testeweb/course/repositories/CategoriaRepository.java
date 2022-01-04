package com.testeweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testeweb.course.domain.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long>{

	

	
	
}
