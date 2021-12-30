package com.testeweb.course.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.testeweb.course.domain.enums.TipoCliente;
@Entity
public class Cliente implements Serializable{
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
	private String email;
	private String cpfOuCnpj;
	
	//inicio das associações
	//associacao
	/*
	 *macete, ela vai ser armazenada interno como um intero, mas externamente vai expor um dado do tipo cliente  
	 * */
	
	private Integer tipo;
	
	/*associacao com telefone ->
	 *  como e uma entidade fraca não precisamos implementar a classe telefone,
	 *  usuaremos set ,pois ele não aceitar repeticoes
	 *  obs: e uma collection
	 */
	@ElementCollection
	@CollectionTable(name="TELEFONE")
	private Set<String> telefones = new HashSet<>();

	
	//associacao com endereco
	@OneToMany(mappedBy = "cliente")
	private List<Endereco> enderecos = new ArrayList<>();

	//fim das associaçoes
	
	//construtores
	public Cliente() {
		
	}



	public Cliente(Long id, String nome, String email, String cpfOuCnpj, TipoCliente tipo) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = tipo.getCod();
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



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}



	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}



	public TipoCliente getTipo() {
		return TipoCliente.toEnum(tipo);
	}



	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo.getCod();
	}



	public Set<String> getTelefones() {
		return telefones;
	}



	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	//hasCode equals

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
		Cliente other = (Cliente) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
	
	
}
