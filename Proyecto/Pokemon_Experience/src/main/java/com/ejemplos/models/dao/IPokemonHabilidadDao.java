package com.ejemplos.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ejemplos.models.entity.PokemonHabilidad;

public interface IPokemonHabilidadDao extends CrudRepository<PokemonHabilidad, Long>{
	
	@Query(value = "SELECT ph FROM PokemonHabilidad ph")
	public List<?> findAllPokemonHabilidades();
	
	@Query(value = "SELECT ph FROM PokemonHabilidad ph WHERE ph.pokemon.idPokemon=:id")
	public List<?> findAllPokemonHabilidades(@Param("id") Long id);
	
}
