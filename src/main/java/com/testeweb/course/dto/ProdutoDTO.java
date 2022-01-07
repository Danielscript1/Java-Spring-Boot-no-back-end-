package com.testeweb.course.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.testeweb.course.domain.Categoria;
import com.testeweb.course.domain.Produto;

public class ProdutoDTO  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
	private Double preco;
	private List<Categoria> categorias = new ArrayList<>();
	
	
	
	public ProdutoDTO() {
		super();
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



	public ProdutoDTO(Produto obj) {
		// TODO Auto-generated constructor stub
	}

}
