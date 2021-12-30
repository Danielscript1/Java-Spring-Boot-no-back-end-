package com.testeweb.course.domain;

import javax.persistence.Entity;

import com.testeweb.course.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComCartao extends Pagamento {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer numeroParcelas;

	
	public PagamentoComCartao() {
		super();
	}
	
	

	public PagamentoComCartao(Long id, EstadoPagamento estado, Pedido pedido,Integer numeroParcelas) {
		super(id, estado, pedido);
		this.numeroParcelas = numeroParcelas;
	}



	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas += numeroParcelas;
	}
	
	
	
	
}
