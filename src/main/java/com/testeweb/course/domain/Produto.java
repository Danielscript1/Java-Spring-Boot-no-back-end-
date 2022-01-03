package com.testeweb.course.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Produto implements Serializable{
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
	private String nome;
	private Double preco;
	
	//Associações (inicie as coleções)
	@JsonBackReference // ela informa que ja esta associado a chamada e ignora a associacao
	@ManyToMany
	 @JoinTable(name = "PRODUTO_CATEGORIA",
	 joinColumns = @JoinColumn(name = "produto_id"),
	 inverseJoinColumns = @JoinColumn(name = "categoria_id")
	 )
	private List<Categoria> categorias = new ArrayList<>();
	
	//associação do tipo chave composta, pedido faz referencia a tabela auxiliar itemPedido, para se comunicar com Produto
	@JsonIgnore
	@OneToMany(mappedBy = "id.produto")
	private Set<ItemPedido> itens = new HashSet<>();//esse tipo de array SET , nao aceita repetição
		
	
	
	//Construtores
	public Produto(Long id, String nome, Double preco) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}
	public Produto() {
		
	}

	//produto conhecer os pedido , associados a ele, entao vamos varrer essa lista
	@JsonIgnore
	public List<Pedido> getPedidos(){
		List<Pedido> lista = new ArrayList<>();
		//agora vamos percorrer minha lista de itens que ja existe,associado aqui nessa classe
		for(ItemPedido x : itens) {
			lista.add(x.getPedido());
		}
		return lista;
	}


	//Getters e setters
	public Set<ItemPedido> getItens() {
		return itens;
	}
	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}
	
	
	//hashCode e equals (implementação padrão: somente id)
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
		Produto other = (Produto) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
	
	
}
