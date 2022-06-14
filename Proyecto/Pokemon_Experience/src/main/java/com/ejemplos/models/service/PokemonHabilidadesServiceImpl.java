package com.ejemplos.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejemplos.models.dao.IPokemonHabilidadDao;
import com.ejemplos.models.entity.PokemonHabilidad;

//Una clase service está basada en el patrón de diseño Facade
// un único punto de acceso a clases DAO. Por cada clase DAO hay una clase Service
@Service
public class PokemonHabilidadesServiceImpl implements IPokemonHabilidadesService {

	// Autowired es para no tener que llamar al contructor, forma parte de spring
	@Autowired
	private IPokemonHabilidadDao pokemonHabilidadDao;

	// el tratamiento de las trasacciones va en la clase service (no en los DAO)
	// también en un método Service podemos usar varios métodos DAO dentro de la
	// misma transacción
	@Override
	@Transactional(readOnly = true)
	public List<PokemonHabilidad> findAll() {
		return (List<PokemonHabilidad>)pokemonHabilidadDao.findAll();
	}

	@Override
	@Transactional
	public void delete(Long id) {
		pokemonHabilidadDao.deleteById(id);
	}

	@Override
	@Transactional
	public void save(PokemonHabilidad pokemon) {
		pokemonHabilidadDao.save(pokemon);
	}

	@Override
	@Transactional(readOnly = true) // readOnlyson lo que recibe los datos
	public PokemonHabilidad findOne(Long id) {
		return pokemonHabilidadDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true) // readOnlyson lo que recibe los datos
	public List<?> findAllPokemonHabilidades() {
		return pokemonHabilidadDao.findAllPokemonHabilidades();
	}
	
	@Override
	@Transactional(readOnly = true) // readOnlyson lo que recibe los datos
	public List<?> findAllPokemonHabilidades(Long id) {
		return pokemonHabilidadDao.findAllPokemonHabilidades(id);
	}
	
}
