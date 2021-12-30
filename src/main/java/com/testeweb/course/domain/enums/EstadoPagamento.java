package com.testeweb.course.domain.enums;

public enum EstadoPagamento {
	PENDENTE(1,"Pendente"),
	QUITADO(2,"Quitado"),
	CANCELADO(3,"Cancelado");

	
	private int cod;
	private String descricao;
	
	private EstadoPagamento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	//tipo enums tem que importar somente os gettes, para não haver modificação nos campos
	
	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	//criar um metodo do tipo estatico , e do tipo static pois permitir ser estanciado 
	//fazer uma operacao que receber o cod e retornar uma operacao do tipo cliente, desejado
	
	public static EstadoPagamento toEnum(Integer cod) {
		//teste de verificação
		if(cod == null) {
			return null;
		}
		//forEach uma buscar 
		for(EstadoPagamento x : EstadoPagamento.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		//lançado minha exerceção caso não encontre o id correspondente
		throw new IllegalArgumentException("Id invalido"+cod);
	}
	
	
}
