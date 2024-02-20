package com.rocket.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rocket.domain.Categoria;
import com.rocket.domain.Cliente;
import com.rocket.domain.OS;
import com.rocket.domain.Tecnico;
import com.rocket.domain.enums.Formapagamento;
import com.rocket.domain.enums.Prioridade;
import com.rocket.domain.enums.Status;
import com.rocket.domain.repositories.CategoriaRepository;
import com.rocket.domain.repositories.ClienteRepository;
import com.rocket.domain.repositories.OSRepository;
import com.rocket.domain.repositories.TecnicoRepository;

@Service
public class DBservice {
	

	@Autowired
	private TecnicoRepository tecnicoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private OSRepository osRepository;
	
	@Autowired
	private CategoriaRepository cateogiraRepository;
	
	public void instanciaDB() {
		
		Tecnico t1 = new Tecnico(null, "Aglae Pereira", "774.841.440-88", null, null, null, null, null, 
				"(17)9888-5895",null, null);
		
		Tecnico t2 = new Tecnico(null, "Francisco Maraci", "774.841.440-88", null, null, null,null, null, 
				"(17)9888-5895",null, null);
		
		
		Cliente c2 = new Cliente(null, "Dione Ferrari", "648.697.488-51", null, null, null, null, null,
				"(17)6325-2535", null, null);

		
		Categoria cat1 = new Categoria(null,"Cliente");
				
		Categoria cat2 = new Categoria(null,"Garantia");
		
		
	//	public OS(Integer id, 
//				  Integer tipodocumento, 
//				  Prioridade prioridade, 
//				  Status status, 
//				 String observacao,
//				  Formapagamento formapagamento,
//				  double valorservico,
//				  Tecnico tecnico, 
//				   Cliente cliente) {
		
		
		
		
		OS oserv = new OS(null, 
				          2, 
				          Prioridade.BAIXA, 
				          Status.ABERTO, 
				          "OS TESTES CRIADA",
				          Formapagamento.AVISTA,
				          100,
				          t1, 
				          c2);

		t1.getList().add(oserv);
		c2.getList().add(oserv);

		tecnicoRepository.saveAll(Arrays.asList(t1,t2));
		clienteRepository.saveAll(Arrays.asList(c2));
		cateogiraRepository.saveAll(Arrays.asList(cat1,cat2));
		osRepository.saveAll(Arrays.asList(oserv));
		
	}
	

}
