package com.ejemplos.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejemplos.models.dao.IPokemonDao;
import com.ejemplos.models.entity.Pokemon;

//Una clase service está basada en el patrón de diseño Facade
// un único punto de acceso a clases DAO. Por cada clase DAO hay una clase Service
@Service
public class PokemonServiceImpl implements IPokemonService {

	// Autowired es para no tener que llamar al contructor, forma parte de spring
	@Autowired
	private IPokemonDao pokemonDao;

	// el tratamiento de las trasacciones va en la clase service (no en los DAO)
	// también en un método Service podemos usar varios métodos DAO dentro de la
	// misma transacción
	@Override
	@Transactional(readOnly = true)
	public List<Pokemon> findAll() {
		return (List<Pokemon>)pokemonDao.findAll();
	}

	@Override
	@Transactional
	public void delete(Long id) {
		pokemonDao.deleteById(id);
	}

	@Override
	@Transactional
	public void save(Pokemon pokemon) {
		pokemonDao.save(pokemon);
	}

	@Override
	@Transactional(readOnly = true) // readOnlyson lo que recibe los datos
	public Pokemon findOne(Long id) {
		return pokemonDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true) // readOnlyson lo que recibe los datos
	public List<?> findAllPokemons() {
		return pokemonDao.findAllPokemons();
	}

	@Override
	@Transactional(readOnly = true) // readOnlyson lo que recibe los datos
	public List<?> findAllPokemonsNombre() {
		return pokemonDao.findAllPokemonsNombre();
	}
	
	@Override
	@Transactional(readOnly = true) // readOnlyson lo que recibe los datos
	public List<?> findAllPokemonsCategoria() {
		return pokemonDao.findAllPokemonsCategoria();
	}
	
	@Override
	@Transactional(readOnly = true) // readOnlyson lo que recibe los datos
	public List<?> findAllTiposPokemon(Long id) {
		return pokemonDao.findAllTiposPokemon(id);
	}
	
	@Override
	@Transactional(readOnly = true) // readOnlyson lo que recibe los datos
	public List<?> findAllPokemonsTipos() {
		return pokemonDao.findAllPokemonsTipos();
	}
	
}
