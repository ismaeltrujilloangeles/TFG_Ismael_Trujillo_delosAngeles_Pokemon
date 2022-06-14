package com.ejemplos.models.service;

import java.util.List;

import com.ejemplos.models.entity.Pokemon;

public interface IPokemonService {
	
	public List<Pokemon> findAll();
	
	public void save(Pokemon pokemon);
	
	public Pokemon findOne(Long id);
	
	public void delete(Long id);
	
	public List<?> findAllPokemons();
	
	public List<?> findAllPokemonsNombre();
	
	public List<?> findAllPokemonsCategoria();
	
	public List<?> findAllTiposPokemon(Long id);
	
	public List<?> findAllPokemonsTipos();
	
}
