package com.rocket.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rocket.domain.Equipamento;
import com.rocket.domain.repositories.EquipamentoRepository;
import com.rocket.dtos.EquipamentoDTO;
import com.rocket.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class EquipamentoService {
	
	@Autowired
	private EquipamentoRepository equipamentoRepository;
	

	public Equipamento findById(Integer id) {
		Optional<Equipamento> obj = equipamentoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: "+id+", Tipo: "+Equipamento.class.getName()   ));
	}
	

	public List<Equipamento> findAll() {
		return equipamentoRepository.findAll();
	}
	
	
	public Equipamento create(EquipamentoDTO objDTO) {		
		return	equipamentoRepository.save(new Equipamento(null,objDTO.getNome(),objDTO.getSerie(), objDTO.getMarca()));	   
	}
	
	
	public @Valid Equipamento update(@Valid EquipamentoDTO obj, Integer id) {
		Equipamento oldObj = findById(id);
		oldObj.setNome(obj.getNome());
		oldObj.setSerie(obj.getSerie());
		oldObj.setMarca(obj.getMarca());
		return equipamentoRepository.save(oldObj);
	}

	
	public void delete(Integer id) {	
		Equipamento obj = findById(id);		
		equipamentoRepository.deleteById(id);				
	}
	
}
