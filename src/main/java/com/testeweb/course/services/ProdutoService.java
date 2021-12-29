package com.testeweb.course.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.testeweb.course.domain.Categoria;
import com.testeweb.course.domain.Produto;
import com.testeweb.course.repositories.ProdutoRepository;
import com.testeweb.course.services.exception.ObjectNotFoundException;
import com.testeweb.course.services.exception.ObjectNotFoundException;
@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	//buscar pelo id
	public Produto buscar(Long id) {
	Optional<Produto> prod = produtoRepository.findById(id);
	/*Checklist de tratamento de exceção de id inválido:
	o Criar ObjectNotFountException
	o Criar StandardError
	o Criar ResourceExceptionHandler 
	 * */
	return prod.orElseThrow(() -> new ObjectNotFoundException( //lançar minha exception pensonalizada
			 "Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
		
	}
}
