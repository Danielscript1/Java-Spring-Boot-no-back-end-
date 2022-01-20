package com.testeweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testeweb.course.domain.Categoria;
import com.testeweb.course.domain.Estado;
import com.testeweb.course.repositories.CategoriaRepository;
import com.testeweb.course.repositories.EstadoRepository;
import com.testeweb.course.services.exception.ObjectNotFoundException;
@Service
public class EstadoService {

	@Autowired
	private EstadoRepository estadoRepository;
	
	//buscar categoria
	
	public Estado buscar(Long id) {
	Optional<Estado> estado = estadoRepository.findById(id); //pode haver ou não um objeto com id correpodente
	
	/*Checklist de tratamento de exceção de id inválido:
	o Criar ObjectNotFountException
	o Criar StandardError
	o Criar ResourceExceptionHandler 
	 * */
	
	return estado.orElseThrow(() -> new ObjectNotFoundException( //lançar minha exception pensonalizada
			 "Objeto não encontrado! Id: " + id + ", Tipo: " + Estado.class.getName()));
	}
	
	//buscar todos os estados
	public List<Estado> findAll(){
		return estadoRepository.findAllByOrderByNome();
	}

}
