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
@Table(name = "tipos")
public class Tipo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTipo;

	@NotEmpty
	private String nombre;

	// bi-directional many-to-one association to Prestamo
	@OneToMany(mappedBy = "tipo")
	private List<PokemonTipo> pokemonsTipos;

	public Long getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(Long id) {
		this.idTipo = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Tipo [idTipo=" + idTipo + ", nombre=" + nombre + "]";
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 1L;

}
