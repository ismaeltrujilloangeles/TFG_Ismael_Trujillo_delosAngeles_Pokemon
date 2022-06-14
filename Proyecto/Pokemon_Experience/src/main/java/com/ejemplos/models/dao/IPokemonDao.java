package com.ejemplos.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ejemplos.models.entity.Pokemon;

public interface IPokemonDao extends CrudRepository<Pokemon, Long>{
	
	@Query(value = "SELECT p.id as idPokemon, p.nombre as nombre_pokemon, p.descripcion, p.imagen, p.idCategoria, "
			+ "t.nombre FROM Pokemon p, Tipo t, PokemonTipo pt WHERE pt.tipo.id=t.id AND pt.pokemon.id=p.id ORDER BY p.id")
	public List<?> findAllPokemons();
	
	@Query(value = "SELECT t.nombre FROM Tipo t, Pokemon pok, PokemonTipo pt WHERE :id=pt.pokemon.idPokemon AND pt.tipo.id=t.id")
	public List<?> findAllTiposPokemon(@Param("id") Long id);
	
	@Query(value = "SELECT p.id as idPokemon, p.nombre as nombre_pokemon, p.descripcion, p.imagen, p.idCategoria, "
			+ "t.nombre FROM Pokemon p, Tipo t, PokemonTipo pt WHERE pt.tipo.id=t.id AND pt.pokemon.id=p.id ORDER BY p.nombre")
	public List<?> findAllPokemonsNombre();
	
	@Query(value = "SELECT p.id as idPokemon, p.nombre as nombre_pokemon, p.descripcion, p.imagen, p.idCategoria, "
			+ "t.nombre FROM Pokemon p, Tipo t, PokemonTipo pt WHERE pt.tipo.id=t.id AND pt.pokemon.id=p.id ORDER BY p.idCategoria.nombre")
	public List<?> findAllPokemonsCategoria();
	
	@Query(value = "SELECT p.id as idPokemon, p.nombre as nombre_pokemon, p.descripcion, p.imagen, p.idCategoria, "
			+ "t.nombre FROM Pokemon p, Tipo t, PokemonTipo pt WHERE pt.tipo.id=t.id AND pt.pokemon.id=p.id ORDER BY t.nombre")
	public List<?> findAllPokemonsTipos();
	
}
