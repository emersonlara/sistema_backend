package com.rocket.resoucers;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rocket.domain.Cliente;
import com.rocket.dtos.ClienteDTO;
import com.rocket.services.ClienteService;

import jakarta.validation.Valid;


@RestController
@RequestMapping(value = "/clientes")
@CrossOrigin("*")
public class ClienteResource {
	
	@Autowired
	private ClienteService ClienteService;
	

	@GetMapping(value ="/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) {		
		Cliente obj = ClienteService.findById(id);
		ClienteDTO objDTO = new ClienteDTO(obj);
		return ResponseEntity.ok().body(objDTO);
	}
	
	@GetMapping()
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<ClienteDTO> list = ClienteService.findAll().stream().map(x -> new ClienteDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public  ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO objDTO){
		
		Cliente newObj = ClienteService.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(newObj.getId()).toUri();	
		return ResponseEntity.created(uri).build();				
	}	
	

	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> update(@Valid @RequestBody ClienteDTO obj, @PathVariable Integer id) {
		obj = new ClienteDTO(ClienteService.update(obj, id));
		return ResponseEntity.ok().build();
	}
	
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){		
		ClienteService.delete(id);
		return ResponseEntity.noContent().build();			
	}
	
}
 