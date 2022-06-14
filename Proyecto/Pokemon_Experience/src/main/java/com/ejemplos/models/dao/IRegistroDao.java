package com.ejemplos.models.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ejemplos.models.entity.Registro;

public interface IRegistroDao extends CrudRepository<Registro, Long>{
	
	@Query(value = "SELECT DISTINCT r FROM Registro r WHERE r.idUsuario like :id")
	public List<?> findAllUnUsuario(Long id);
	
	@Query(value = "SELECT r FROM Registro r ORDER BY r.nombreUsuario")
	public List<?> findAllRegistrosUsuarios();
	
	@Query(value = "SELECT r FROM Registro r WHERE r.resultado like 'Victoria'")
	public List<?> findAllRegistrosVictoria();
	
	@Query(value = "SELECT r FROM Registro r WHERE r.resultado like 'Derrota'")
	public List<?> findAllRegistrosDerrota();
	
	@Query(value = "SELECT r FROM Registro r WHERE r.resultado like 'Empate'")
	public List<?> findAllRegistrosEmpate();
	
	@Query(value = "SELECT r FROM Registro r ORDER BY r.nombrePokemon")
	public List<?> findAllRegistrosElegido();
	
	@Query(value = "SELECT r FROM Registro r ORDER BY r.nombrePokemonCPU")
	public List<?> findAllRegistrosContrincante();
	
}

