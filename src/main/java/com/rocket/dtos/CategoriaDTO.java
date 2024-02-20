package com.rocket.dtos;

import java.io.Serializable;

import com.rocket.domain.Categoria;


public class CategoriaDTO  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
   public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

private Integer id;
	
  // @NotEmpty(message="O campo Descricao é obrigatório")
   private String descricao;
   
   

	public CategoriaDTO() {
		super();
	}

	public CategoriaDTO(Categoria obj) {
		super();
		this.id = obj.getId();
		this.descricao = obj.getDescricao();		
	}


}
