package com.rocket.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rocket.domain.Tecnico;

@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico,Integer> {

}
