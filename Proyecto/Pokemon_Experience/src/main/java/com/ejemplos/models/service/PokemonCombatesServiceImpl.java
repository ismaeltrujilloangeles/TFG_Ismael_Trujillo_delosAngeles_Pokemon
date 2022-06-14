package com.ejemplos.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejemplos.models.dao.IPokemonCombatesDao;
import com.ejemplos.models.entity.PokemonCombates;

//Una clase service está basada en el patrón de diseño Facade
// un único punto de acceso a clases DAO. Por cada clase DAO hay una clase Service
@Service
public class PokemonCombatesServiceImpl implements IPokemonCombatesService {

	// Autowired es para no tener que llamar al contructor, forma parte de spring
	@Autowired
	private IPokemonCombatesDao pokemonCombatesDao;

	// el tratamiento de las trasacciones va en la clase service (no en los DAO)
	// también en un método Service podemos usar varios métodos DAO dentro de la
	// misma transacción
	@Override
	@Transactional(readOnly = true)
	public List<PokemonCombates> findAll() {
		return (List<PokemonCombates>)pokemonCombatesDao.findAll();
	}

	@Override
	@Transactional
	public void delete(Long id) {
		pokemonCombatesDao.deleteById(id);
	}

	@Override
	@Transactional
	public void save(PokemonCombates pokemon) {
		pokemonCombatesDao.save(pokemon);
	}

	@Override
	@Transactional(readOnly = true) // readOnlyson lo que recibe los datos
	public PokemonCombates findOne(Long id) {
		return pokemonCombatesDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true) // readOnlyson lo que recibe los datos
	public List<?> findAllPokemonsIDS() {
		return pokemonCombatesDao.findAllPokemonsIDS();
	}
	
	@Override
	@Transactional(readOnly = true) // readOnlyson lo que recibe los datos
	public List<?> findAllPokemonsCombates() {
		return pokemonCombatesDao.findAllPokemonsCombates();
	}

	@Override
	@Transactional(readOnly = true) // readOnlyson lo que recibe los datos
	public List<?> findAllPokemonsCombatesHP() {
		return pokemonCombatesDao.findAllPokemonsCombatesHP();
	}
	
	@Override
	@Transactional(readOnly = true) // readOnlyson lo que recibe los datos
	public List<?> findAllPokemonsCombatesAttack() {
		return pokemonCombatesDao.findAllPokemonsCombatesAttack();
	}
	
	@Override
	@Transactional(readOnly = true) // readOnlyson lo que recibe los datos
	public List<?> findAllPokemonsCombatesDefense() {
		return pokemonCombatesDao.findAllPokemonsCombatesDefense();
	}
	
	@Override
	@Transactional(readOnly = true) // readOnlyson lo que recibe los datos
	public List<?> findAllPokemonsCombatesSpAttack() {
		return pokemonCombatesDao.findAllPokemonsCombatesSpAttack();
	}
	
	@Override
	@Transactional(readOnly = true) // readOnlyson lo que recibe los datos
	public List<?> findAllPokemonsCombatesSpDefense() {
		return pokemonCombatesDao.findAllPokemonsCombatesSpDefense();
	}
	
	@Override
	@Transactional(readOnly = true) // readOnlyson lo que recibe los datos
	public List<?> findAllPokemonsCombatesSpeed() {
		return pokemonCombatesDao.findAllPokemonsCombatesSpeed();
	}
	
}
