package com.rocket.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rocket.domain.Pessoa;
import com.rocket.domain.Tecnico;
import com.rocket.domain.repositories.PessoaRepository;
import com.rocket.domain.repositories.TecnicoRepository;
import com.rocket.dtos.TecnicoDTO;
import com.rocket.services.exceptions.DataIntegratyViolationException;
import com.rocket.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = tecnicoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: "+id+", Tipo: "+Tecnico.class.getName()   ));
	}

	public List<Tecnico> findAll() {
		return tecnicoRepository.findAll();
	}
	
	public Tecnico create(TecnicoDTO objDTO) {
		
    //Tecnico newObj = new Tecnico(null, objDTO.getNome(),objDTO.getCpf(),objDTO.getTelefone(), null, null, null, null, null, null, null );
    //return tecnicoRepository.save(newObj);
		
	if(findByCPF(objDTO) != null) {
		throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
	}
	return	tecnicoRepository.save(new Tecnico(null, 
			                                   objDTO.getNome(),
			                                   objDTO.getCpf(),
			                                   null, 
			                                   null, 
			                                   null, 
			                                   null, 
			                                   null, 
			                                   objDTO.getTelefone(), 
			                                   null, 
			                                   null));	   
	}
	
	
	public Pessoa  findByCPF(TecnicoDTO objDTO){
		Pessoa  obj = pessoaRepository.findByCPF(objDTO.getCpf());
		if(obj != null) {
			return obj;
		}
		return null;	
	}
	
	

	public @Valid Tecnico update(@Valid TecnicoDTO obj, Integer id) {
		Tecnico oldObj = findById(id);

		if (findByCPF(obj) != null && findByCPF(obj).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}

		oldObj.setCpf(obj.getCpf());
		oldObj.setNome(obj.getNome());
		oldObj.setTelefone(obj.getTelefone());
		//obj.getPerfis().forEach(x -> oldObj.addPerfil(x));
		return tecnicoRepository.save(oldObj);
	}

	public void delete(Integer id) {	
		Tecnico obj = findById(id);
		if(obj.getList().size() > 0) {
	   	   throw new DataIntegratyViolationException("Tecnico possui orden(s) de serviço , não pode ser deletado!");
		}
		tecnicoRepository.deleteById(id);				
	}
		
}
