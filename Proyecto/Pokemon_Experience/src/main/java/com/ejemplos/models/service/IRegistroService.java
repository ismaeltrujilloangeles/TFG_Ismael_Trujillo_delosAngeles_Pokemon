package com.ejemplos.models.service;

import java.util.List;

import com.ejemplos.models.entity.Registro;

public interface IRegistroService {
	
	public List<Registro> findAll();
	
	public void save(Registro registro);
	
	public Registro findOne(Long id);
	
	public void delete(Long id);
	
	public List<?> findAllUnUsuario(Long id);
	
	public List<?> findAllRegistrosUsuarios();
	
	public List<?> findAllRegistrosVictoria();
	
	public List<?> findAllRegistrosDerrota();
	
	public List<?> findAllRegistrosEmpate();
	
	public List<?> findAllRegistrosElegido();
	
	public List<?> findAllRegistrosContrincante();
}
