package com.rocket.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity(name="TECNICO")
public class Tecnico extends Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@OneToMany(mappedBy = "tecnico")
	private List<OS> list = new ArrayList<>();
	public Tecnico() {
		super();

	}

	public Tecnico(Integer id, String nome, String cpf, String endereco, String numero, String cep, String cidade,
			String estado, String telefone, String celular, String email) {
		super(id, nome, cpf, endereco, numero, cep, cidade, estado, telefone, celular, email);
	}

	public List<OS> getList() {
		return list;
	}

	public void setList(List<OS> list) {
		this.list = list;
	}	
	
	

}
