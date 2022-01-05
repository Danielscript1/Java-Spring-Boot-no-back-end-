package com.testeweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.testeweb.course.domain.Cliente;
import com.testeweb.course.domain.Cliente;
import com.testeweb.course.dto.ClienteDTO;
import com.testeweb.course.dto.ClienteDTO;
import com.testeweb.course.repositories.ClienteRepository;
import com.testeweb.course.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	//buscar cliente
	
	public Cliente find(Long id) {
	Optional<Cliente> cliente = clienteRepository.findById(id); //pode haver ou não um objeto com id correpodente
	
	/*Checklist de tratamento de exceção de id inválido:
	o Criar ObjectNotFountException
	o Criar StandardError
	o Criar ResourceExceptionHandler 
	 * */
	
	return cliente.orElseThrow(() -> new ObjectNotFoundException( //lançar minha exception pensonalizada
			 "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	//atualizar
	public Cliente update( Cliente obj,Long id) {
		//instancia um cliente apartir do banco de dados, para ele trazer os objetos ja existente ,junto com dto
		Cliente newObj = find(obj.getId());//pegar meus dados do banco
		updateData(newObj,obj);//atualizar meus dados com base nos dados que vieram do obj
		return clienteRepository.save(newObj);
	}
	//minha função auxiliar que ataulizar os dados
	private void updateData(Cliente newObj, Cliente obj) {
		//atualizar meu newObj com base nesse novos obj
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

	//deletar
	public void delete(Long id) {
		try {
		find(id);//verifcar se esse id existe
	    clienteRepository.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new com.testeweb.course.services.exception.DataIntegrityViolationException("não e possivel excluir porque há entidades relacionadas");
		}
	}
	//listando todas as clientes
	public List<Cliente> findAll() {
		
		return clienteRepository.findAll();
	}
	
	//adicionando paginação
	public Page<Cliente>findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		//obj que vai prepara minha informaçoes, para fzer a consulta
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
		return clienteRepository.findAll(pageRequest);
	}
	
	
	
	//metodo de conversao , apartir de um objDTO que recebir la do resource , vou converter para obj cliente
		public Cliente fromDto(ClienteDTO  objDto) {
			return new Cliente(objDto.getId(),objDto.getNome(), objDto.getEmail(), null, null);
			
			
		}
}
