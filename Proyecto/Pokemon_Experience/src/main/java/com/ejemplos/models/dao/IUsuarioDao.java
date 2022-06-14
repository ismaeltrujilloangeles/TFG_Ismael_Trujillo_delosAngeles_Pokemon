package com.ejemplos.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ejemplos.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long>{
	
	@Query(value = "SELECT DISTINCT u FROM Usuario u WHERE u.usuario like :nombreUsuario AND u.contrasena like :contrasena")
	public Usuario findUsuario(String nombreUsuario, String contrasena);
	
	@Query(value = "SELECT DISTINCT u FROM Usuario u ORDER BY u.nombre")
	public List<Usuario> findAllUsuariosNombres();
	
	@Query(value = "SELECT DISTINCT u FROM Usuario u ORDER BY u.apellidos")
	public List<Usuario> findAllUsuariosApellidos();
	
	@Query(value = "SELECT DISTINCT u FROM Usuario u ORDER BY u.usuario")
	public List<Usuario> findAllUsuariosUsuarios();
	
	@Query(value = "SELECT DISTINCT u FROM Usuario u ORDER BY u.edad DESC")
	public List<Usuario> findAllUsuariosEdades();
	
}
