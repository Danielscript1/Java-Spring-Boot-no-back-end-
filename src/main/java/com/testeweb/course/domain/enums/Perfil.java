package com.testeweb.course.domain.enums;

/*
 * inportante voce definir uma regra para seu tipo enumerador, para não correr risco de alguem alterar seu padrao, aqui definimos 
 * o tipo padrao correspondente a cada enumeracao definir um status
 * 1 - Adminitrador
 * 2 - cliente 
 * */
public enum Perfil {
	ADMIN(1,"ROLE_ADMIN"),
	CLIENTE(2,"ROLE_CLIENTE");
	
	private int cod;
	private String descricao;
	
	private Perfil(int cod, String descricao) {
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
	
	public static Perfil toEnum(Integer cod) {
		//teste de verificação
		if(cod == null) {
			return null;
		}
		//forEach uma buscar 
		for(Perfil x : Perfil.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		//lançado minha exerceção caso não encontre o id correspondente
		throw new IllegalArgumentException("Id invalido"+cod);
	}
	
	
}
