package com.testeweb.course.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.testeweb.course.domain.Produto;
import com.testeweb.course.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	//buscar pelo id
	public Produto buscar(Long id) {
	Optional<Produto> prod = produtoRepository.findById(id);	
	return prod.orElse(null);
		
	}
}
