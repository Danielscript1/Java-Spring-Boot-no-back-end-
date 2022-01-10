package com.testeweb.course.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testeweb.course.domain.pk.ItemPedidoPK;
@Entity
public class ItemPedido implements Serializable {
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
	//referencia a tabela com id ->chave composta
	@JsonIgnore
	@EmbeddedId
	private ItemPedidoPK id = new ItemPedidoPK();
	private Double desconto;
	private Integer quantidade;
	private Double preco;
	

	//construtores
	public ItemPedido() {
		
	}


	public ItemPedido(Pedido pedido,Produto produto ,Double desconto, Integer quantidade, Double preco) {
		id.setPedido(pedido);//adicionando pedido que vem no argumento do construtor
		id.setProduto(produto); //adicionando o produto que vem com argumento no construtor
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}
	
	@JsonIgnore
	public Pedido getPedido() {
		return id.getPedido();
	}
	//para associar o itempedido ,ao pedido
	public void setPedido(Pedido pedido) {
		id.setPedido(pedido);
	}
	
	public Produto getProduto() {
		return id.getProduto();
	}
	//associar o itemproduto , ao produto
	public void setProduto(Produto produto) {
		id.setProduto(produto);
	}
	public ItemPedidoPK getId() {
		return id;
	}


	public void setId(ItemPedidoPK id) {
		this.id = id;
	}


	public Double getDesconto() {
		return desconto;
	}


	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}


	public Integer getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}


	public Double getPreco() {
		return preco;
	}


	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	//calculo do subTotal Item pedido
	public Double getSuperTotal() {
		return ( preco - desconto ) * quantidade;
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
		ItemPedido other = (ItemPedido) obj;
		return Objects.equals(id, other.id);
	}

	//tostring
	@Override
	public String toString() {
		//criar uma intancia de dinheiro, obs classe propria do java, para formatações
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));
		StringBuilder builder = new StringBuilder();
		builder.append(getProduto().getNome());
		builder.append(", Qte: ");
		builder.append(getQuantidade());
		builder.append(", Preço unitario: ");
		builder.append(nf.format(getPreco()));
		builder.append(", SubTotal: ");
		builder.append(nf.format(getSuperTotal()));
		builder.append("\n");
		return builder.toString();
	}

	

	
	


	
	
	
	
	
}
