package com.ejemplos.models.service;

import java.util.List;

import com.ejemplos.models.entity.PokemonCombates;

public interface IPokemonCombatesService {
	
	public List<PokemonCombates> findAll();
	
	public void save(PokemonCombates pokemon);
	
	public PokemonCombates findOne(Long id);
	
	public void delete(Long id);
	
	public List<?> findAllPokemonsIDS();
	
	public List<?> findAllPokemonsCombates();

	public List<?> findAllPokemonsCombatesHP();
	
	public List<?> findAllPokemonsCombatesAttack();
	
	public List<?> findAllPokemonsCombatesDefense();
	
	public List<?> findAllPokemonsCombatesSpAttack();
	
	public List<?> findAllPokemonsCombatesSpDefense();
	
	public List<?> findAllPokemonsCombatesSpeed();
}
