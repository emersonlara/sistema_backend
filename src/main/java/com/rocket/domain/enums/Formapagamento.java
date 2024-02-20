package com.rocket.domain.enums;

public enum Formapagamento {

	AVISTA(0,"AVISTA"),
	PRAZO_CARTEIRA(1,"CARTEIRA"),
	PRAZO_CARTAO_DEBITO(2,"CARTAO DEBITO"),
	PRAZO_CARTAO_CREDITO(3,"CARTAO CREDITO"),
	PRAZO_BOLETO(4,"BOLETO"),
	PIX(5,"PIX"),
	CORTESIA(6,"CORTESIA"),
	GARANTIA(7,"GARANTIA");
	
	
	private Integer cod;
	private String  descricao;
	
    private Formapagamento(Integer cod, String descricao) {
    	this.cod = cod;
    	this.descricao = descricao;    	
    }

	public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
    

	public static Formapagamento toEnum(Integer cod) {
		if(cod == null) { 
			return null;     	
		}
		
		for( Formapagamento x : Formapagamento.values()) {			
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Forma de pagamento inválida"+cod);
	}		
 }



