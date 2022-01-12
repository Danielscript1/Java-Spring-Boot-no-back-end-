package com.testeweb.course.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Pedido implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * Checklist para criar entidades:
	o Atributos básicos
	o Associações (inicie as coleções)
	o Construtores (não inclua coleções no construtor com parâmetros)
	o Getters e setters
	o hashCode e equals (implementação padrão: somente id)
	o Serializable   = e uma interface que falar que os objetos dela pode ser convetidos em bytes
	 * */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date instante;
	
	//Associações (inicie as coleções)
	@OneToOne(mappedBy = "pedido",cascade = CascadeType.ALL)
	private Pagamento pagamento;
	
	@ManyToOne
	@JoinColumn(name="endereco_de_entrega_id")
	private Endereco enderecoDeEntrega;
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	//associação do tipo chave composta, pedido faz referencia a tabela auxiliar itemPedido, para se comunicar com Produto
	@OneToMany(mappedBy = "id.pedido")
	private Set<ItemPedido> items = new HashSet<>();//esse tipo de array SET , nao aceita repetição
	
	
	//Construtores (não inclua coleções no construtor com parâmetros)
	public Pedido() {
		
	}

	

	public Pedido(Long id, Date instante, Cliente cliente, Endereco enderecoDeEntrega) {
		
		this.id = id;
		this.instante = instante;
		this.enderecoDeEntrega = enderecoDeEntrega;
		this.cliente = cliente;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getInstante() {
		return instante;
	}

	public void setInstante(Date instante) {
		this.instante = instante;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	

	public Endereco getEnderecoDeEntrega() {
		return enderecoDeEntrega;
	}



	public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
		this.enderecoDeEntrega = enderecoDeEntrega;
	}



	public Cliente getCliente() {
		return cliente;
	}



	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Set<ItemPedido> getItems() {
		return items;
	}



	public void setItems(Set<ItemPedido> items) {
		this.items = items;
	}
	
	//total do pedido
	public Double getValorTotal() {
		Double soma = 0.0;
		for(ItemPedido ip : items) {
			soma = soma + ip.getSuperTotal();
		}
		return soma;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



	@Override
	public String toString() {
		//formatar data
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		StringBuilder builder = new StringBuilder();
		builder.append("Pedido numero: ");
		builder.append(getId());
		builder.append(", instante: ");
		builder.append(sdf.format(getInstante()));
		builder.append(", cliente: ");
		builder.append(getCliente().getNome());
		builder.append(", situação do pagamento: ");
		builder.append(getPagamento().getEstado().getDescricao());
		builder.append("\n Detalhes: \n");
		for(ItemPedido ip: getItems()) {
			builder.append(ip.toString());
		}
		builder.append("valor total: ");
		builder.append(getValorTotal());
		return builder.toString();
	}


	
	
	
	
	
	
}
