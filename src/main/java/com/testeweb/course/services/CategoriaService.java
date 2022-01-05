package com.testeweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.testeweb.course.domain.Categoria;
import com.testeweb.course.domain.Cliente;
import com.testeweb.course.dto.CategoriaDTO;
import com.testeweb.course.repositories.CategoriaRepository;
import com.testeweb.course.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	//buscar todas as categoria
	
	public Categoria find(Long id) {
	Optional<Categoria> cat = categoriaRepository.findById(id); //pode haver ou não um objeto com id correpodente
	
	/*Checklist de tratamento de exceção de id inválido:
	o Criar ObjectNotFountException
	o Criar StandardError
	o Criar ResourceExceptionHandler 
	 * */
	
	return cat.orElseThrow(() -> new ObjectNotFoundException( //lançar minha exception pensonalizada
			 "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	//metodo de inserir
	public Categoria insert(Categoria obj) {
		obj.setId(null);// vai considerar que estou inserindo um dados, pois se o ID, nao for nulo ele considerar que vai ser uma atualização
		return categoriaRepository.save(obj);
	}
	
	//atualizar
	public Categoria update( Categoria obj,Long id) {
		//instancia um cliente apartir do banco de dados, para ele trazer os objetos ja existente ,junto com dto
		Categoria newObj = find(obj.getId());//pegar meus dados do banco
		updateData(newObj,obj);//atualizar meus dados com base nos dados que vieram do obj
		return categoriaRepository.save(newObj);
	}
	//metodo auxiliar do update
	private void updateData(Categoria newObj, Categoria obj) {
		//atualizar meu newObj com base nesse novos obj
		newObj.setNome(obj.getNome());
		
	}

	//deletar
	public void delete(Long id) {
		try {
		find(id);//verifcar se esse id existe
	    categoriaRepository.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new com.testeweb.course.services.exception.DataIntegrityViolationException("não e possivel excluir uma categoria que possui produtos");
		}
	}
	//listando todas as categorias
	public List<Categoria> findAll() {
		
		return categoriaRepository.findAll();
	}
	
	//adicionando paginação
	public Page<Categoria>findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		//obj que vai prepara minha informaçoes, para fzer a consulta
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
		return categoriaRepository.findAll(pageRequest);
	}
	
	//metodo de conversao , apartir de um objDTO que recebir la do resource , vou converter para obj categoria
	public Categoria fromDto(CategoriaDTO  objDto) {
		return new Categoria(objDto.getId(),objDto.getNome());
		
	}

	
}
