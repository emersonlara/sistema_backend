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

import com.rocket.domain.Equipamento;
import com.rocket.dtos.EquipamentoDTO;
import com.rocket.services.EquipamentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/equipamentos")
@CrossOrigin("*")
public class EquipamentoResource {
	
	@Autowired
	private EquipamentoService equipamentoService;
	
	
	@GetMapping(value ="/{id}")
	public ResponseEntity<EquipamentoDTO> findById(@PathVariable Integer id) {		
		Equipamento obj = equipamentoService.findById(id);
		EquipamentoDTO objDTO = new EquipamentoDTO(obj);
		return ResponseEntity.ok().body(objDTO);
	}
	
	@GetMapping()
	public ResponseEntity<List<EquipamentoDTO>> findAll() {
		List<EquipamentoDTO> list = equipamentoService.findAll().stream().map(x -> new EquipamentoDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(list);
	}
	
	
	@PostMapping
	public  ResponseEntity<EquipamentoDTO> create(@Valid @RequestBody EquipamentoDTO objDTO){
		
		Equipamento newObj = equipamentoService.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(newObj.getId()).toUri();	
		return ResponseEntity.created(uri).build();				
	}	
	
	

	@PutMapping(value = "/{id}")
	public ResponseEntity<EquipamentoDTO> update(@Valid @RequestBody EquipamentoDTO obj, @PathVariable Integer id) {
		obj = new EquipamentoDTO(equipamentoService.update(obj, id));
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){		
		equipamentoService.delete(id);
		return ResponseEntity.noContent().build();			
	}
	
	
	

}
