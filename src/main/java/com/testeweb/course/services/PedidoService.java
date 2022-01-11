package com.testeweb.course.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.testeweb.course.domain.ItemPedido;
import com.testeweb.course.domain.PagamentoComBoleto;
import com.testeweb.course.domain.Pedido;
import com.testeweb.course.domain.enums.EstadoPagamento;
import com.testeweb.course.repositories.ClienteRepository;
import com.testeweb.course.repositories.ItemPedidoRepository;
import com.testeweb.course.repositories.PagamentoRepository;
import com.testeweb.course.repositories.PedidoRepository;
import com.testeweb.course.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private BoletoService boletoService;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private EmailService emailService;
	//buscar categoria
	
	public Pedido find(Long id) {
	Optional<Pedido> pedido = pedidoRepository.findById(id); //pode haver ou não um objeto com id correpodente
	
	/*Checklist de tratamento de exceção de id inválido:
	o Criar ObjectNotFountException
	o Criar StandardError
	o Criar ResourceExceptionHandler 
	 * */
	
	return pedido.orElseThrow(() -> new ObjectNotFoundException( //lançar minha exception pensonalizada
			 "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	 @Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);//garantir que estou inserindo um novo pedido
		obj.setInstante(new Date()); //setado data do pedido 
		obj.setCliente(clienteService.find(obj.getCliente().getId()));//buscar o id , do banco de dados, para vir todos as informaçoes do cliente
		//setando o estado do pagamento
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		//associação de mao dupla , o pagamento tem que conhecer o pedido dele
		obj.getPagamento().setPedido(obj);
		//se o pagamento for com boleto
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamanetoComBoleto(pagto,obj.getInstante());
		}
		//salvando pedido
		obj = pedidoRepository.save(obj);
		pagamentoRepository.save(obj.getPagamento());//salvando pagamento
		
		//salvando itemPedido
		for (ItemPedido ip : obj.getItems()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.find(ip.getProduto().getId()));//busquei o produto do banco de dados, agora meu items esta associado ao produto que busquei do banco
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItems());
		emailService.sendOrderConfirmationHtmlEmail(obj);
		return obj;
	}
}
