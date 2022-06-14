package com.ejemplos.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejemplos.models.dao.ITipoDao;
import com.ejemplos.models.entity.Tipo;

//Una clase service está basada en el patrón de diseño Facade
// un único punto de acceso a clases DAO. Por cada clase DAO hay una clase Service
@Service
public class TipoServiceImpl implements ITipoService {

	// Autowired es para no tener que llamar al contructor, forma parte de spring
	@Autowired
	private ITipoDao tipoDao;

	// el tratamiento de las trasacciones va en la clase service (no en los DAO)
	// también en un método Service podemos usar varios métodos DAO dentro de la
	// misma transacción
	@Override
	@Transactional(readOnly = true)
	public List<Tipo> findAll() {
		return (List<Tipo>)tipoDao.findAll();
	}

	@Override
	@Transactional
	public void delete(Long id) {
		tipoDao.deleteById(id);
	}

	@Override
	@Transactional
	public void save(Tipo tipo) {
		tipoDao.save(tipo);
	}

	@Override
	@Transactional(readOnly = true) // readOnlyson lo que recibe los datos
	public Tipo findOne(Long id) {
		return tipoDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true) // readOnlyson lo que recibe los datos
	public List<Tipo> findAllTipos() {
		return tipoDao.findAllTipos();
	}

}
