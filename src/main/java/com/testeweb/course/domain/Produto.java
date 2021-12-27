package com.testeweb.course.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	@ManyToMany
	 @JoinTable(name = "PRODUTO_CATEGORIA",
	 joinColumns = @JoinColumn(name = "produto_id"),
	 inverseJoinColumns = @JoinColumn(name = "categoria_id")
	 )
	private List<Categoria> categorias = new ArrayList<>();
	
	//Construtores
	public Produto(Long id, String nome, Double preco) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}
	public Produto() {
		
	}

	


	//Getters e setters
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
