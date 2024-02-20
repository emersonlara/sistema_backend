package com.rocket.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rocket.domain.Categoria;
import com.rocket.domain.repositories.CategoriaRepository;
import com.rocket.dtos.CategoriaDTO;
import com.rocket.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	/*
	public Categoria findById(Integer id) {
		Optional<Categoria> obj = Optional.empty();
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: "+id+", Tipo: "+Categoria.class.getName()   ));
	}
	*/
	
	public Categoria findById(Integer id) {
		
		Optional<Categoria> obj = categoriaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: "+id+", Tipo: "+Categoria.class.getName()   ));				
	}
	
	
	
	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}
	
	
	public Categoria create(CategoriaDTO objDTO) {		
		return	categoriaRepository.save(new Categoria(null,objDTO.getDescricao()));	   
	}
	
	public @Valid Categoria update(@Valid CategoriaDTO obj, Integer id) {
		Categoria oldObj = findById(id);
		oldObj.setDescricao(obj.getDescricao());		
		return categoriaRepository.save(oldObj);
	}

	public void delete(Integer id) {	
		Categoria obj = findById(id);		
		categoriaRepository.deleteById(id);				
	}
	
}
