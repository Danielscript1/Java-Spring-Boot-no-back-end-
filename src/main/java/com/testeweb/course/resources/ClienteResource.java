package com.testeweb.course.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.testeweb.course.domain.Cliente;
import com.testeweb.course.domain.Cliente;
import com.testeweb.course.dto.CategoriaDTO;
import com.testeweb.course.dto.ClienteDTO;
import com.testeweb.course.dto.ClienteNewDTO;
import com.testeweb.course.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService clienteService; 
	
	//buscar cliente pelo Id
	@PreAuthorize("hasAnyRole('ADMIN')") 
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> buscarId(@PathVariable Long id) {
		Cliente cliente = clienteService.find(id);
		return ResponseEntity.ok().body(cliente);
	}
	
	
	//insert processo de execução
		@PostMapping
		public ResponseEntity<Cliente> insert(@Validated @RequestBody ClienteNewDTO  objDto){ //anotacao REquestBody , faz que o json seja convertido para objeto java
			//antes de chmar meu metodo insert, tenho que converter meu obj dto para um tipo de entity
			Cliente obj = clienteService.fromDto(objDto);
			obj = clienteService.insert(obj);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri(); //estou pegando o caminho na url e construindo um novo junto com id que vai ser criado
			return ResponseEntity.created(uri).build();
		}
	
	//atualizando cliente
		@PutMapping(value="/{id}")
		public ResponseEntity<Cliente> update(@Validated @RequestBody ClienteDTO objDto,@PathVariable Long id) {
			Cliente obj = clienteService.fromDto(objDto);
			obj.setId(id);//garantiar que vem o id desejado 
			obj = clienteService.update(obj, obj.getId());
			return ResponseEntity.noContent().build();
		}
		
		//deletar
		@PreAuthorize("hasAnyRole('ADMIN')") 
		@DeleteMapping(value="/{id}")
		public ResponseEntity<Cliente> deletar(@PathVariable Long id){
			clienteService.delete(id);
			return ResponseEntity.noContent().build();
		}
		
		//Buscar com paginação
		//listando todas as clientes
			@PreAuthorize("hasAnyRole('ADMIN')") 
			@GetMapping(value="/page")
			public ResponseEntity<Page<ClienteDTO>> findPage(
					@RequestParam(value="page",defaultValue = "0") Integer  page,
					@RequestParam(value="linesPerPage",defaultValue = "24") Integer linesPerPage,
					@RequestParam(value="orderBy",defaultValue = "nome")String orderBy,
					@RequestParam(value="direction",defaultValue = "ASC")String direction) {
				Page<Cliente> list = clienteService.findPage(page,linesPerPage,orderBy,direction);
				Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj)); //obs: page ja é java8complime ele, não precisar do metodos de strem e collections
				return ResponseEntity.ok().body(listDto);
			}

}