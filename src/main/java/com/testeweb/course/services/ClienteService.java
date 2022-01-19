package com.testeweb.course.services;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.testeweb.course.domain.Cidade;
import com.testeweb.course.domain.Cliente;
import com.testeweb.course.domain.Endereco;
import com.testeweb.course.domain.enums.Perfil;
import com.testeweb.course.domain.enums.TipoCliente;
import com.testeweb.course.dto.ClienteDTO;
import com.testeweb.course.dto.ClienteNewDTO;
import com.testeweb.course.repositories.ClienteRepository;
import com.testeweb.course.repositories.EnderecoRepository;
import com.testeweb.course.security.UserSS;
import com.testeweb.course.services.exception.AuthorizationException;
import com.testeweb.course.services.exception.ObjectNotFoundException;
@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	//buscar cliente
	@Autowired
	private  BCryptPasswordEncoder be;
	@Autowired
	private S3Service s3Service;
	
	public Cliente find(Long id) {
		
	/*Restricao cliente so recupera as coisas associadas  a ele*/
		
	//se esse usuario que , eu busquei não possui o perfil de admin	
		UserSS user = UserService.authenticated();
		if(user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			 throw new AuthorizationException("acesso negado");
		}
	Optional<Cliente> cliente = clienteRepository.findById(id); //pode haver ou não um objeto com id correpodente
	
	/*Checklist de tratamento de exceção de id inválido:
	o Criar ObjectNotFountException
	o Criar StandardError
	o Criar ResourceExceptionHandler 
	 * */
	
	return cliente.orElseThrow(() -> new ObjectNotFoundException( //lançar minha exception pensonalizada
			 "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	// essa anotação vai garantir , que ele vai salvar tanto os cliente como o endereco na mesma transação no banco de dados
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = clienteRepository.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
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
			return new Cliente(objDto.getId(),objDto.getNome(), objDto.getEmail(), null, null,null);
			
			
		}
		//metodo de conversao , apartir de um objDTO que recebir la do resource , vou converter para obj cliente
		public Cliente fromDto(ClienteNewDTO objDto) {
			// cliente
			Cliente cliente = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()),be.encode(objDto.getSenha()));
			//cidade
			Cidade cidade = new Cidade(objDto.getCidadeId(),null,null);
			//Endereco
			Endereco end = new Endereco(null,objDto.getLogradouro(),objDto.getNumero(),objDto.getComplemento(),objDto.getBairro(),objDto.getCep(),cliente,cidade);
			//adicionando as associaçoes
			cliente.getEnderecos().add(end);
			
			cliente.getTelefones().add(objDto.getTelefone1());
			//fazendo um teste de verificação
			if(objDto.getTelefone2() != null) {
				cliente.getTelefones().add(objDto.getTelefone2());
			}
			if(objDto.getTelefone3() != null) {
				cliente.getTelefones().add(objDto.getTelefone3());
			}
			
			//retorna cliente;
			return cliente;
		}
		
		//upload de arquivos ou img
		public URI uploadProfilePicture(MultipartFile multipartFile) {
			return s3Service.uploadFile(multipartFile);
		}
		
}
