package com.testeweb.course;

import java.text.SimpleDateFormat;
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
import com.testeweb.course.domain.ItemPedido;
import com.testeweb.course.domain.Pagamento;
import com.testeweb.course.domain.PagamentoComBoleto;
import com.testeweb.course.domain.PagamentoComCartao;
import com.testeweb.course.domain.Pedido;
import com.testeweb.course.domain.Produto;
import com.testeweb.course.domain.enums.EstadoPagamento;
import com.testeweb.course.domain.enums.TipoCliente;
import com.testeweb.course.repositories.CategoriaRepository;
import com.testeweb.course.repositories.CidadeRepository;
import com.testeweb.course.repositories.ClienteRepository;
import com.testeweb.course.repositories.EnderecoRepository;
import com.testeweb.course.repositories.EstadoRepository;
import com.testeweb.course.repositories.ItemPedidoRepository;
import com.testeweb.course.repositories.PagamentoRepository;
import com.testeweb.course.repositories.PedidoRepository;
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
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemRepository;
	
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
		
		
		
		//instancia de pedido
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null,sdf.parse("30/09/2021 10:32"),e1,cliente1);
		Pedido ped2 = new Pedido(null,sdf.parse("30/09/2021 19:35"),e1,cliente1);
		
		//instancia do Pagamento
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1,6);
		ped1.setPagamento(pagto1);//associacao ped1 com pagamento 1 -> adicionando o pagamento
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2,sdf.parse("20/10/2017 00:00"),null);
		ped2.setPagamento(pagto2);//associacao ped2 com pagamento 2 -> adicionando o pagamento
		
		
		//instancia ItemPedido 
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
				
		
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
		
		
		
	
		
		
		//associar cliente com pedido 
		cliente1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		//pedido com endereco -> adicionando endereco
		ped1.setEnderecoDeEntrega(e1);
		ped2.setEnderecoDeEntrega(e2);
		
		/*cada pedido conhecer seus itens*/
		//associação  pedido com items
		ped1.getItems().addAll(Arrays.asList(ip1,ip2));
		//associação pedido com itens
		ped2.getItems().addAll(Arrays.asList(ip3));
		
		/*cada produto conhecendo seu itens*/
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		
		
		//salvando no banco de Dados
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		clienteRepository.saveAll(Arrays.asList(cliente1));
		estadoRepository.saveAll(Arrays.asList(estado1,estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade1,cidade2,cidade3));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
		itemRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		
		
	}

}
