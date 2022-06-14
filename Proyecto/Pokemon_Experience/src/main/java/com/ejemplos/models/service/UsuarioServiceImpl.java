package com.ejemplos.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejemplos.models.dao.IUsuarioDao;
import com.ejemplos.models.entity.Usuario;

//Una clase service está basada en el patrón de diseño Facade
// un único punto de acceso a clases DAO. Por cada clase DAO hay una clase Service
@Service
public class UsuarioServiceImpl implements IUsuarioService {

	// Autowired es para no tener que llamar al contructor, forma parte de spring
	@Autowired
	private IUsuarioDao usuarioDao;

	// el tratamiento de las trasacciones va en la clase service (no en los DAO)
	// también en un método Service podemos usar varios métodos DAO dentro de la
	// misma transacción
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return (List<Usuario>)usuarioDao.findAll();
	}

	@Override
	@Transactional
	public void delete(Long id) {
		usuarioDao.deleteById(id);
	}

	@Override
	@Transactional
	public void save(Usuario usuario) {
		usuarioDao.save(usuario);
	}

	@Override
	@Transactional(readOnly = true) // readOnlyson lo que recibe los datos
	public Usuario findOne(Long id) {
		return usuarioDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true) // readOnlyson lo que recibe los datos
	public Usuario findUsuario(String nombreUsuario, String contrasena) {
		return (Usuario) usuarioDao.findUsuario(nombreUsuario, contrasena);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAllUsuariosNombres() {
		return (List<Usuario>)usuarioDao.findAllUsuariosNombres();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAllUsuariosApellidos() {
		return (List<Usuario>)usuarioDao.findAllUsuariosApellidos();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAllUsuariosUsuarios() {
		return (List<Usuario>)usuarioDao.findAllUsuariosUsuarios();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAllUsuariosEdades() {
		return (List<Usuario>)usuarioDao.findAllUsuariosEdades();
	}
	
}
