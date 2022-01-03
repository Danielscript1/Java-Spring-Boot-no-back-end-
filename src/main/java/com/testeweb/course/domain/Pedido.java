package com.testeweb.course.domain;

import java.io.Serializable;
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
	@JsonIgnore
	private Cliente cliente;
	
	//associação do tipo chave composta, pedido faz referencia a tabela auxiliar itemPedido, para se comunicar com Produto
	@OneToMany(mappedBy = "id.pedido")
	private Set<ItemPedido> items = new HashSet<>();//esse tipo de array SET , nao aceita repetição
	
	
	//Construtores (não inclua coleções no construtor com parâmetros)
	public Pedido() {
		
	}

	

	public Pedido(Long id, Date instante, Endereco enderecoDeEntrega, Cliente cliente) {
		
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
	



	@Override
	public int hashCode() {
		return Objects.hash(id);
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
		return Objects.equals(id, other.id);
	}



	
	
	
	
	
}