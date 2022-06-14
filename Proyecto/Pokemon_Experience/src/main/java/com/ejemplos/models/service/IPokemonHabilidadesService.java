package com.ejemplos.models.service;

import java.util.List;

import com.ejemplos.models.entity.PokemonHabilidad;

public interface IPokemonHabilidadesService {
	
	public List<PokemonHabilidad> findAll();
	
	public void save(PokemonHabilidad pokemon);
	
	public PokemonHabilidad findOne(Long id);
	
	public void delete(Long id);
	
	public List<?> findAllPokemonHabilidades();
	
	public List<?> findAllPokemonHabilidades(Long id);
	
}
