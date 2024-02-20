package com.rocket.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rocket.domain.Pessoa;
import com.rocket.domain.Cliente;
import com.rocket.domain.repositories.PessoaRepository;
import com.rocket.domain.repositories.ClienteRepository;
import com.rocket.dtos.ClienteDTO;
import com.rocket.services.exceptions.DataIntegratyViolationException;
import com.rocket.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public Cliente findById(Integer id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: "+id+", Tipo: "+Cliente.class.getName()   ));
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}
	
	public Cliente create(ClienteDTO objDTO) {
		
    //Cliente newObj = new Cliente(null, objDTO.getNome(),objDTO.getCpf(),objDTO.getTelefone(), null, null, null, null, null, null, null );
    //return ClienteRepository.save(newObj);
		
	if(findByCPF(objDTO) != null) {
		throw new DataIntegratyViolationException("CPF já cadastrado na base dados!");
	}
	return	clienteRepository.save(new Cliente(null, 
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
	
	
	public Pessoa  findByCPF(ClienteDTO objDTO){
		Pessoa  obj = pessoaRepository.findByCPF(objDTO.getCpf());
		if(obj != null) {
			return obj;
		}
		return null;	
	}
	
	

	public @Valid Cliente update(@Valid ClienteDTO obj, Integer id) {
		Cliente oldObj = findById(id);

		if (findByCPF(obj) != null && findByCPF(obj).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base dados!");
		}

		oldObj.setCpf(obj.getCpf());
		oldObj.setNome(obj.getNome());
		oldObj.setTelefone(obj.getTelefone());
		//obj.getPerfis().forEach(x -> oldObj.addPerfil(x));
		return clienteRepository.save(oldObj);
	}

	public void delete(Integer id) {	
		Cliente obj = findById(id);
		if(obj.getList().size() > 0) {
	   	   throw new DataIntegratyViolationException("Pessoa possui orden(s) de serviço , não pode ser deletado!");
		}
		clienteRepository.deleteById(id);				
	}
		
}
