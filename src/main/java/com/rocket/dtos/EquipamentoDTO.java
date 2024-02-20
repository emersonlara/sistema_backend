package com.rocket.dtos;

import java.io.Serializable;

import com.rocket.domain.Equipamento;

import jakarta.validation.constraints.NotEmpty;

public class EquipamentoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	
    @NotEmpty(message="O campo Descricao obrigatório")
	private String nome;
    
    @NotEmpty(message="O campo Serie obrigatório")
	private String serie;
    
    
    @NotEmpty(message="O campo Marca obrigatório")
	private String marca;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
		
	public EquipamentoDTO() {
		super();
	}
		
	public EquipamentoDTO(Equipamento obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.serie = obj.getSerie();
		this.marca = obj.getMarca();		
	}
	
}
