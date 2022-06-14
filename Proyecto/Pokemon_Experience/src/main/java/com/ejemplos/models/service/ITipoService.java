package com.ejemplos.models.service;

import java.util.List;

import com.ejemplos.models.entity.Tipo;

public interface ITipoService {
	
	public List<Tipo> findAll();
	
	public void save(Tipo tipo);
	
	public Tipo findOne(Long id);
	
	public void delete(Long id);
	
	public List<Tipo> findAllTipos();
}
