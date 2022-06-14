package com.ejemplos.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;

	@NotEmpty
	private String nombre;

	@NotEmpty
	private String apellidos;

	@NotEmpty
	private String usuario;

	@NotEmpty
	private String contrasena;
	
	@NotNull
	private Long edad;

	public Usuario() {
		
	}
	
	public Usuario(String nombre2, String apellidos2, String nombreUsuario, String contrasena2, Long edad2) {
		this.nombre=nombre2;
		this.apellidos=apellidos2;
		this.usuario = nombreUsuario;
		this.contrasena = contrasena2;
		this.edad = edad2;
	}

	public Long getId() {
		return idUsuario;
	}

	public void setId(Long id) {
		this.idUsuario = id;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
	public Long getEdad() {
		return edad;
	}

	public void setEdad(Long edad) {
		this.edad = edad;
	}


	@Override
	public String toString() {
		return "Usuarios [idUsuario=" + idUsuario + ", nombre=" + nombre + ", apellidos=" + apellidos + ", usuario=" + usuario
				+ ", contrasena=" + contrasena + ", edad=" + edad + "]";
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 1L;

}
