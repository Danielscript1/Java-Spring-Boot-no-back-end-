package com.testeweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.testeweb.course.domain.Categoria;
import com.testeweb.course.domain.Produto;
import com.testeweb.course.repositories.CategoriaRepository;
import com.testeweb.course.repositories.ProdutoRepository;
import com.testeweb.course.services.exception.ObjectNotFoundException;
@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private  CategoriaRepository categoriaRepository;
	
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
	
	
	//adicionando paginação
	public Page<Produto>findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		//obj que vai prepara minha informaçoes, para fzer a consulta
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
		return produtoRepository.findAll(pageRequest);
	}
	
	/*Classe ProdutoService:
	Parâmetros:
	nome: um trecho de nome de produto
	ids: uma lista de códigos de categorias
	Retorno:
	A listagem de produtos que contém o trecho de nome dado e que pertencem a pelo menos uma das
	categorias dadas
	 * */
	
	public Page<Produto> search(String nome, List<Long> ids,Integer page, Integer linesPerPage, String orderBy, String direction){
		//obj que vai prepara minha informaçoes, para fzer a consulta
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
		//implementar minha lista,que buscar meus id , lá do banco
		List<Categoria> categorias = categoriaRepository.findAllById(ids);//vou isntancia com os ids que estão no banco
		return produtoRepository.search(nome,categorias,pageRequest);
	}
}
