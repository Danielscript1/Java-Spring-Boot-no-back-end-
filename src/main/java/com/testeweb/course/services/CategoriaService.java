package com.testeweb.course.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testeweb.course.domain.Categoria;
import com.testeweb.course.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	//buscar categoria
	public Categoria buscar(Long id) {
	Optional<Categoria> cat = categoriaRepository.findById(id); //pode haver ou não um objeto com id correpodente
	return cat.orElse(null);// se nao encontrar nada retona null
	}
}
