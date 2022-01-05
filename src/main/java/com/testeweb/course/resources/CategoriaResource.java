package com.testeweb.course.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.testeweb.course.domain.Categoria;
import com.testeweb.course.dto.CategoriaDTO;
import com.testeweb.course.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService categoriaService; 
	
	//listando todas as categorias
	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		List<Categoria> list = categoriaService.findAll();
		//converter minha lista recebida , para dto, buscando os paramentros desejados
		/*
		 * Quando usar MAP em java?
		Essa interface é um objeto que mapeia valores para chaves,
		ou seja, através da chave consegue ser acessado o valor configurado,
		sendo que a chave não pode ser repetida ao contrário do valor,
		mas se caso tiver uma chave repetida é sobrescrito pela última chamada. Também faz parte do pacote java.
		 * 
		 * */
		
		List<CategoriaDTO> listDto = list.stream().map(
		 obj -> new CategoriaDTO(obj)		
				).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	
	
	
	//pesquisando por Id
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Long id) {
		Categoria cat = categoriaService.find(id);
		return ResponseEntity.ok().body(cat);
	}
	
	
	//insert processo de execução
	@PostMapping
	public ResponseEntity<Categoria> insert(@Validated @RequestBody CategoriaDTO  objDto){ //anotacao REquestBody , faz que o json seja convertido para objeto java
		//antes de chmar meu metodo insert, tenho que converter meu obj dto para um tipo de entity
		Categoria obj = categoriaService.fromDto(objDto);
		obj = categoriaService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
		.path("/{id}").buildAndExpand(obj.getId()).toUri(); //estou pegando o caminho na url e construindo um novo junto com id que vai ser criado
		return ResponseEntity.created(uri).build();
	}
	
	//atualizando categoria
	@PutMapping(value="/{id}")
	public ResponseEntity<Categoria> update(@Validated @RequestBody CategoriaDTO objDto,@PathVariable Long id) {
		Categoria obj = categoriaService.fromDto(objDto);
		obj.setId(id);//garantiar que vem o id desejado 
		obj = categoriaService.update(obj, obj.getId());
		return ResponseEntity.noContent().build();
	}
	
	//deletar
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Categoria> deletar(@PathVariable Long id){
		categoriaService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	//Buscar com paginação
	//listando todas as categorias
		@GetMapping(value="/page")
		public ResponseEntity<Page<CategoriaDTO>> findPage(
				@RequestParam(value="page",defaultValue = "0") Integer  page,
				@RequestParam(value="linesPerPage",defaultValue = "24") Integer linesPerPage,
				@RequestParam(value="orderBy",defaultValue = "nome")String orderBy,
				@RequestParam(value="direction",defaultValue = "ASC")String direction) {
			Page<Categoria> list = categoriaService.findPage(page,linesPerPage,orderBy,direction);
			Page<CategoriaDTO> listDto = list.map(obj -> new CategoriaDTO(obj)); //obs: page ja é java8complime ele, não precisar do metodos de strem e collections
			return ResponseEntity.ok().body(listDto);
		}
		

}