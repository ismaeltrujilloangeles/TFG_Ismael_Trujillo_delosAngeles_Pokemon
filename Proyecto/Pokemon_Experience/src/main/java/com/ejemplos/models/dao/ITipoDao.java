package com.ejemplos.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ejemplos.models.entity.Pokemon;
import com.ejemplos.models.entity.Tipo;

public interface ITipoDao extends CrudRepository<Tipo, Long>{
	
	@Query(value = "SELECT DISTINCT t FROM Tipo t")
	public List<Tipo> findAllTipos();
	
}
