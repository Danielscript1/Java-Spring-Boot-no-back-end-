package com.testeweb.course.services;

import java.util.Optional;

import com.testeweb.course.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testeweb.course.domain.Categoria;
import com.testeweb.course.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	//buscar categoria
	
	public Categoria find(Long id) {
	Optional<Categoria> cat = categoriaRepository.findById(id); //pode haver ou não um objeto com id correpodente
	
	/*Checklist de tratamento de exceção de id inválido:
	o Criar ObjectNotFountException
	o Criar StandardError
	o Criar ResourceExceptionHandler 
	 * */
	
	return cat.orElseThrow(() -> new ObjectNotFoundException( //lançar minha exception pensonalizada
			 "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	//metodo de inserir
	public Categoria insert(Categoria obj) {
		obj.setId(null);// vai considerar que estou inserindo um dados, pois se o ID, nao for nulo ele considerar que vai ser uma atualização
		return categoriaRepository.save(obj);
	}
	
	//ataulizar
	public Categoria update( Categoria obj,Long id) {
		find(obj.getId());//lançar minha exercption pensonalizada do metodo find 
		return categoriaRepository.save(obj);
	}

	
}
