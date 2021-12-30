package com.testeweb.course;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.testeweb.course.domain.Categoria;
import com.testeweb.course.domain.Cidade;
import com.testeweb.course.domain.Cliente;
import com.testeweb.course.domain.Endereco;
import com.testeweb.course.domain.Estado;
import com.testeweb.course.domain.Produto;
import com.testeweb.course.domain.enums.TipoCliente;
import com.testeweb.course.repositories.CategoriaRepository;
import com.testeweb.course.repositories.CidadeRepository;
import com.testeweb.course.repositories.ClienteRepository;
import com.testeweb.course.repositories.EnderecoRepository;
import com.testeweb.course.repositories.EstadoRepository;
import com.testeweb.course.repositories.ProdutoRepository;

@SpringBootApplication
public class SpringBootbackendApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootbackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//instancias de produto e categoria
		Categoria cat1 = new Categoria(null,"informatica");
		Categoria cat2 = new Categoria(null,"escritorio");
		Produto p1 = new Produto(null,"computador",2000.00);
		Produto p2 = new Produto(null,"impressora",800.00);
		Produto p3 = new Produto(null,"mouse",120.00);
		
		//instancias de estado e cidade 
		Estado estado1 = new Estado(null,"Minas gerais");
		Estado estado2 = new Estado(null,"sao paulo");
		Cidade cidade1 = new Cidade (null,"Uberlandia", estado1);
		Cidade cidade2 = new Cidade (null,"sao paulo", estado2);
		Cidade cidade3 = new Cidade (null,"campinas", estado2);
		
		//instancia do cliente e Endereco e cidade
		Cliente cliente1 = new Cliente(null,"maria silva","maria@gmail.com","05630489965",TipoCliente.toEnum(1));
		Endereco e1 = new Endereco(null,"rua juiz carvalho","3485","novohorizonte","dirceu2","64075656",cliente1,cidade1);
		Endereco e2 = new Endereco(null,"rua venom","6595","lugarnenhum","dirceu","4585320",cliente1,cidade2);
		
		//associando produto na categoria
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		//associando categoria ao produto
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1));
		p3.getCategorias().addAll(Arrays.asList(cat2));
		
		//associando estado para cidade
		
		estado1.getCidade().addAll(Arrays.asList(cidade1));
		estado2.getCidade().addAll(Arrays.asList(cidade2));
		estado2.getCidade().addAll(Arrays.asList(cidade3));
		
		//associando cliente ao telefone
		cliente1.getTelefones().addAll(Arrays.asList("94848542","94290123")); 
		
		//associando cliente ao endereço
		cliente1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		
		
		
		//salvando no banco de Dados
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		clienteRepository.saveAll(Arrays.asList(cliente1));
		estadoRepository.saveAll(Arrays.asList(estado1,estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade1,cidade2,cidade3));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
	}

}
