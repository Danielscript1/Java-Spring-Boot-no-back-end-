package com.testeweb.course.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testeweb.course.domain.Categoria;
import com.testeweb.course.domain.Cidade;
import com.testeweb.course.repositories.CidadeRepository;
import com.testeweb.course.services.exception.ObjectNotFoundException;
@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	//buscar categoria
	
	public Cidade buscar(Long id) {
	Optional<Cidade> cidade = cidadeRepository.findById(id); //pode haver ou não um objeto com id correpodente
	
	/*Checklist de tratamento de exceção de id inválido:
	o Criar ObjectNotFountException
	o Criar StandardError
	o Criar ResourceExceptionHandler 
	 * */
	
	return cidade.orElseThrow(() -> new ObjectNotFoundException( //lançar minha exception pensonalizada
			 "Objeto não encontrado! Id: " + id + ", Tipo: " + Cidade.class.getName()));
	}

}
