package com.rocket.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity(name = "CLIENTE")
public class Cliente extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<OS> list = new ArrayList<>();

	public Cliente() {
		super();
	}

	public Cliente(Integer id, String nome, String cpf, String endereco, String numero, String cep, String cidade,
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
