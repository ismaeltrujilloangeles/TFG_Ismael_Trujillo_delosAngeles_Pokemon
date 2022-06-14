package com.ejemplos.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ejemplos.models.entity.Categoria;

public interface ICategoriaDao extends CrudRepository<Categoria, Long>{
	
}
