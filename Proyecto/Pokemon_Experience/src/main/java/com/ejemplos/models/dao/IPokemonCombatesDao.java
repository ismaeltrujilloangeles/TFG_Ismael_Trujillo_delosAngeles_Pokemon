package com.ejemplos.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ejemplos.models.entity.PokemonCombates;

public interface IPokemonCombatesDao extends CrudRepository<PokemonCombates, Long>{
	
	@Query(value = "SELECT pc.id FROM PokemonCombates pc ORDER BY pc.id")
	public List<?> findAllPokemonsIDS();
	
	@Query(value = "SELECT pc.id as idPokemon, pc.HP as vida, pc.attack as ataque, pc.defense as defensa, pc.spattack as ataque_especial, "
			+ "pc.spdefense as defensa_especial, pc.speed as velocidad, "
			+ "(SELECT p.nombre FROM Pokemon p WHERE pc.id=p.id) "
			+ "FROM PokemonCombates pc ORDER BY pc.id")
	public List<?> findAllPokemonsCombates();
	
	@Query(value = "SELECT pc.id as idPokemon, pc.HP as vida, pc.attack as ataque, pc.defense as defensa, pc.spattack as ataque_especial, "
			+ "pc.spdefense as defensa_especial, pc.speed as velocidad,"
			+ "(SELECT DISTINCT p.nombre FROM Pokemon p WHERE p.id = pc.id)"
			+ "FROM PokemonCombates pc ORDER BY pc.HP DESC")
	public List<?> findAllPokemonsCombatesHP();
	
	@Query(value = "SELECT pc.id as idPokemon, pc.HP as vida, pc.attack as ataque, pc.defense as defensa, pc.spattack as ataque_especial, "
			+ "pc.spdefense as defensa_especial, pc.speed as velocidad,"
			+ "(SELECT DISTINCT p.nombre FROM Pokemon p WHERE p.id = pc.id)"
			+ "FROM PokemonCombates pc ORDER BY pc.attack DESC")
	public List<?> findAllPokemonsCombatesAttack();
	
	@Query(value = "SELECT pc.id as idPokemon, pc.HP as vida, pc.attack as ataque, pc.defense as defensa, pc.spattack as ataque_especial, "
			+ "pc.spdefense as defensa_especial, pc.speed as velocidad,"
			+ "(SELECT DISTINCT p.nombre FROM Pokemon p WHERE p.id = pc.id)"
			+ "FROM PokemonCombates pc ORDER BY pc.defense DESC")
	public List<?> findAllPokemonsCombatesDefense();
	
	@Query(value = "SELECT pc.id as idPokemon, pc.HP as vida, pc.attack as ataque, pc.defense as defensa, pc.spattack as ataque_especial, "
			+ "pc.spdefense as defensa_especial, pc.speed as velocidad,"
			+ "(SELECT DISTINCT p.nombre FROM Pokemon p WHERE p.id = pc.id)"
			+ "FROM PokemonCombates pc ORDER BY pc.spattack DESC")
	public List<?> findAllPokemonsCombatesSpAttack();
	
	@Query(value = "SELECT pc.id as idPokemon, pc.HP as vida, pc.attack as ataque, pc.defense as defensa, pc.spattack as ataque_especial, "
			+ "pc.spdefense as defensa_especial, pc.speed as velocidad,"
			+ "(SELECT DISTINCT p.nombre FROM Pokemon p WHERE p.id = pc.id)"
			+ "FROM PokemonCombates pc ORDER BY pc.spdefense DESC")
	public List<?> findAllPokemonsCombatesSpDefense();
	
	@Query(value = "SELECT pc.id as idPokemon, pc.HP as vida, pc.attack as ataque, pc.defense as defensa, pc.spattack as ataque_especial, "
			+ "pc.spdefense as defensa_especial, pc.speed as velocidad,"
			+ "(SELECT DISTINCT p.nombre FROM Pokemon p WHERE p.id = pc.id)"
			+ "FROM PokemonCombates pc ORDER BY pc.speed DESC")
	public List<?> findAllPokemonsCombatesSpeed();
	
}
