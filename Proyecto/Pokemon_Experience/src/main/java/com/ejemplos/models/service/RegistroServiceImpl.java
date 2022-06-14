package com.ejemplos.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejemplos.models.dao.IRegistroDao;
import com.ejemplos.models.entity.Registro;

//Una clase service está basada en el patrón de diseño Facade
// un único punto de acceso a clases DAO. Por cada clase DAO hay una clase Service
@Service
public class RegistroServiceImpl implements IRegistroService {

	// Autowired es para no tener que llamar al contructor, forma parte de spring
	@Autowired
	private IRegistroDao registroDao;

	// el tratamiento de las trasacciones va en la clase service (no en los DAO)
	// también en un método Service podemos usar varios métodos DAO dentro de la
	// misma transacción
	@Override
	@Transactional(readOnly = true)
	public List<Registro> findAll() {
		return (List<Registro>)registroDao.findAll();
	}

	@Override
	@Transactional
	public void delete(Long id) {
		registroDao.deleteById(id);
	}

	@Override
	@Transactional
	public void save(Registro registro) {
		registroDao.save(registro);
	}

	@Override
	@Transactional(readOnly = true) // readOnlyson lo que recibe los datos
	public Registro findOne(Long id) {
		return registroDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true) // readOnlyson lo que recibe los datos
	public List<?> findAllUnUsuario(Long id) {
		return registroDao.findAllUnUsuario(id);
	}
	
	@Override
	@Transactional(readOnly = true) // readOnlyson lo que recibe los datos
	public List<?> findAllRegistrosVictoria() {
		return registroDao.findAllRegistrosVictoria();
	}
	
	@Override
	@Transactional(readOnly = true) // readOnlyson lo que recibe los datos
	public List<?> findAllRegistrosDerrota() {
		return registroDao.findAllRegistrosDerrota();
	}
	
	@Override
	@Transactional(readOnly = true) // readOnlyson lo que recibe los datos
	public List<?> findAllRegistrosEmpate() {
		return registroDao.findAllRegistrosEmpate();
	}
	
	@Override
	@Transactional(readOnly = true) // readOnlyson lo que recibe los datos
	public List<?> findAllRegistrosElegido() {
		return registroDao.findAllRegistrosElegido();
	}
	
	@Override
	@Transactional(readOnly = true) // readOnlyson lo que recibe los datos
	public List<?> findAllRegistrosContrincante() {
		return registroDao.findAllRegistrosContrincante();
	}
	
	@Override
	@Transactional(readOnly = true) // readOnlyson lo que recibe los datos
	public List<?> findAllRegistrosUsuarios() {
		return registroDao.findAllRegistrosUsuarios();
	}
}