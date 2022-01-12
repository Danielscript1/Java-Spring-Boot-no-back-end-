package com.testeweb.course.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.testeweb.course.services.validation.ClienteInsert;
@ClienteInsert //anotação customizada, uma validação que vai selecionar CpfouCnpj,dependedo do tipo, vai ser uma anotação da classe
public class ClienteNewDTO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//PARTE DO CLIENTE
	@NotEmpty(message = "Preenchimento do campo obrigatorio")
	@Length(min=5,max=80,message = "o tamanho deve ser entre 5 e 80 caractere")
	private String nome;
	@Email(message = "Email, não pode esta em Branco!")
	private String email;
	@Email(message = "Email, não pode esta em Branco!")
	private String senha;
	@NotEmpty(message = "Preenchimento do campo obrigatorio")
	private String cpfOuCnpj;
	private Integer tipo;
	//PARTE DO ENDERECO
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	//PARTE DO TELEFONE
	@NotEmpty(message = "Preenchimento do campo obrigatorio")
	private String telefone1;
	private String telefone2;
	private String telefone3;
	//ESCOLHER O CODIGO DE UMA CIDADE
	private Long cidadeId;
	
	//construtor padão
	public ClienteNewDTO() {
		
	}
	
	public ClienteNewDTO(ClienteNewDTO  objDto) {
		
	}
	
	//GettesSettes
	
	public String getNome() {
		return nome;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
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

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelegone1(String telegone1) {
		this.telefone1 = telegone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}

	public Long getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Long cidadeId) {
		this.cidadeId = cidadeId;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	
}
