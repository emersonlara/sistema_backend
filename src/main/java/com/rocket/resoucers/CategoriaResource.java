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
import com.rocket.domain.Categoria;
import com.rocket.dtos.CategoriaDTO;
import com.rocket.services.CategoriaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/categorias")
@CrossOrigin("*")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService categoriaService;
	
	
	@GetMapping(value ="/{id}")
	public ResponseEntity<CategoriaDTO> findById(@PathVariable Integer id) {		
		Categoria obj = categoriaService.findById(id);
		CategoriaDTO objDTO = new CategoriaDTO(obj);
		return ResponseEntity.ok().body(objDTO);
	}
	
		
	
	
	
	@GetMapping()
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		List<CategoriaDTO> list = categoriaService.findAll().stream().map(x -> new CategoriaDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public  ResponseEntity<CategoriaDTO> create(@Valid @RequestBody CategoriaDTO objDTO){
		
		Categoria newObj = categoriaService.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(newObj.getId()).toUri();	
		return ResponseEntity.created(uri).build();				
	}	
	
	

	@PutMapping(value = "/{id}")
	public ResponseEntity<CategoriaDTO> update(@Valid @RequestBody CategoriaDTO obj, @PathVariable Integer id) {
		obj = new CategoriaDTO(categoriaService.update(obj, id));
		return ResponseEntity.ok().build();
	}
	
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){		
		categoriaService.delete(id);
		return ResponseEntity.noContent().build();			
	}
	
	

}
