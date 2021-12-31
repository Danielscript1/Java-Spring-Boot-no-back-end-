package com.testeweb.course.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testeweb.course.domain.enums.EstadoPagamento;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)//anotacao para definir herança InheritanceType.SINGLE_TABLE ou InheritanceType.JOINED
public abstract class Pagamento implements Serializable{
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
	private Long id;
	//TIPO ENUMERADO
	private Integer estado;
   //associação
	/*
	 *Achei esta nota também útil: @MapsId na anotação de hibernação mapeia uma coluna com a coluna de outra tabela.

		Também pode ser usado para compartilhar a mesma chave primária entre 2 tabelas. 
		
		@MapsId -> GARANTE QUE EU VOU TER O MESMO iD CORRESPONDENTE AO PEDIDO
	 * */
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="pedido_id")
	@MapsId
	private Pedido pedido;
	
	public Pagamento() {
		
	}

	public Pagamento(Long id, EstadoPagamento estado,Pedido pedido) {
	
		this.id = id;
		this.estado = estado.getCod();
		this.pedido = pedido;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EstadoPagamento getEstado() {
		return EstadoPagamento.toEnum(estado);
	}

	public void setEstado(EstadoPagamento estado) {
		this.estado = estado.getCod();
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
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
		Pagamento other = (Pagamento) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
	
	
}
