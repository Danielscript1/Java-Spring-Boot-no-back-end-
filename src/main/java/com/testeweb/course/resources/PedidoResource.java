package com.testeweb.course.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.testeweb.course.domain.Categoria;
import com.testeweb.course.domain.Pedido;
import com.testeweb.course.dto.CategoriaDTO;
import com.testeweb.course.services.PedidoService;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService pedidoService; 
	
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pedido> buscarId(@PathVariable Long id) {
		Pedido pedido = pedidoService.find(id);
		return ResponseEntity.ok().body(pedido);
	}
	
	//insert processo de execução
	@PostMapping
	public ResponseEntity<Void> insert(@Validated @RequestBody Pedido  obj){ //anotacao REquestBody , faz que o json seja convertido para objeto java
		
		obj = pedidoService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
		.path("/{id}").buildAndExpand(obj.getId()).toUri(); //estou pegando o caminho na url e construindo um novo junto com id que vai ser criado
		return ResponseEntity.created(uri).build();
	}
	
	//Buscar com paginação
		//listando todas as categorias
			@GetMapping
			public ResponseEntity<Page<Pedido>> findPage(
					@RequestParam(value="page",defaultValue = "0") Integer  page,
					@RequestParam(value="linesPerPage",defaultValue = "24") Integer linesPerPage,
					@RequestParam(value="orderBy",defaultValue = "nome")String orderBy,
					@RequestParam(value="direction",defaultValue = "ASC")String direction) {
				Page<Pedido> list = pedidoService.findPage(page,linesPerPage,orderBy,direction);
				
				return ResponseEntity.ok().body(list);
			}

}