package com.ejemplos.models.service;

import java.util.List;

import com.ejemplos.models.entity.Usuario;

public interface IUsuarioService {
	
	public List<Usuario> findAll();
	
	public void save(Usuario usuario);
	
	public Usuario findOne(Long id);
	
	public void delete(Long id);
	
	public Usuario findUsuario(String nombreUsuario, String contrasena);
	
	public List<Usuario> findAllUsuariosNombres();
	
	public List<Usuario> findAllUsuariosApellidos();
	
	public List<Usuario> findAllUsuariosUsuarios();
	
	public List<Usuario> findAllUsuariosEdades();
}
