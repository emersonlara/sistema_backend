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

import com.rocket.domain.Tecnico;
import com.rocket.dtos.TecnicoDTO;
import com.rocket.services.TecnicoService;

import jakarta.validation.Valid;


@RestController
@RequestMapping(value = "/tecnicos")
@CrossOrigin("*")
public class TecnicoResource {
	
	@Autowired
	private TecnicoService tecnicoService;
	

	@GetMapping(value ="/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {		
		Tecnico obj = tecnicoService.findById(id);
		TecnicoDTO objDTO = new TecnicoDTO(obj);
		return ResponseEntity.ok().body(objDTO);
	}
	
	@GetMapping()
	public ResponseEntity<List<TecnicoDTO>> findAll() {
		List<TecnicoDTO> list = tecnicoService.findAll().stream().map(x -> new TecnicoDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public  ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO objDTO){
		
		Tecnico newObj = tecnicoService.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(newObj.getId()).toUri();	
		return ResponseEntity.created(uri).build();				
	}	
	

	@PutMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> update(@Valid @RequestBody TecnicoDTO obj, @PathVariable Integer id) {
		obj = new TecnicoDTO(tecnicoService.update(obj, id));
		return ResponseEntity.ok().build();
	}
	
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){		
		tecnicoService.delete(id);
		return ResponseEntity.noContent().build();			
	}
	
}
 