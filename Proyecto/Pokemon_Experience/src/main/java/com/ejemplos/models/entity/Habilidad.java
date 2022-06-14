package com.ejemplos.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.*;

@Entity
@Table(name = "habilidades")
public class Habilidad implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idHabilidad;

	@NotEmpty
	private String nombre;

	// bi-directional many-to-one association to Prestamo
	@OneToMany(mappedBy = "habilidad")
	private List<PokemonHabilidad> pokemonsHabilidades;

	public Long getIdHabilidad() {
		return idHabilidad;
	}

	public void setIdHabilidad(Long id) {
		this.idHabilidad = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Habilidad [idHabilidad=" + idHabilidad + ", nombre=" + nombre + "]";
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 1L;

}
