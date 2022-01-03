package com.testeweb.course.domain.pk;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.testeweb.course.domain.Pedido;
import com.testeweb.course.domain.Produto;

//CHAVE COMPOSTA
@Embeddable //informando que ela vai ser um subtipo
public class ItemPedidoPK implements Serializable{
	/**
	 * 
	 */
	//obs o mapiamento é manyToOne -> pois faz referencia muito pedido e a tabela auxiliar que faz referencia a um 
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name="pedido_id")
	private Pedido pedido;
	@ManyToOne
	@JoinColumn(name="produto_id")
	private Produto produto;
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedio) {
		this.pedido = pedido;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	@Override
	public int hashCode() {
		return Objects.hash(pedido, produto);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedidoPK other = (ItemPedidoPK) obj;
		return Objects.equals(pedido, other.pedido) && Objects.equals(produto, other.produto);
	}
	
	
	
}
