package com.testeweb.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.testeweb.course.domain.Categoria;
import com.testeweb.course.domain.Produto;
import com.testeweb.course.dto.CategoriaDTO;
import com.testeweb.course.dto.ProdutoDTO;
import com.testeweb.course.resources.utils.URL;
import com.testeweb.course.services.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {
	@Autowired
	private ProdutoService produtoService;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Produto> buscarId(@PathVariable Long id) {
		Produto prod = produtoService.buscar(id);
		return ResponseEntity.ok().body(prod);
	}
	
	//Buscar com paginação
		//listando todas as categorias
			@GetMapping
			public ResponseEntity<Page<ProdutoDTO>> findPage(
					@RequestParam(value="nome",defaultValue = "") String  nome,
					@RequestParam(value="categorias",defaultValue = "0") String  categorias,
					@RequestParam(value="page",defaultValue = "0") Integer  page,
					@RequestParam(value="linesPerPage",defaultValue = "24") Integer linesPerPage,
					@RequestParam(value="orderBy",defaultValue = "nome")String orderBy,
					@RequestParam(value="direction",defaultValue = "ASC")String direction){
				//ESSA LISTA VAI RECEBER O PARAMENTRO QUE VEIO ,NO MEU METODO
				String nomeDecoded = URL.decodeParam(nome);
				List<Long> ids = URL.decodeIntList(categorias);
				Page<Produto> list = produtoService.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
				Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj)); //obs: page ja é java8complime ele, não precisar do metodos de strem e collections
				return ResponseEntity.ok().body(listDto);
			}
}
