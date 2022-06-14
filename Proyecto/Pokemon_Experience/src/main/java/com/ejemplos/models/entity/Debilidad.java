package com.ejemplos.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.*;

import org.springframework.data.repository.query.parser.Part.Type;

@Entity
@Table(name = "debilidades")
public class Debilidad implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idDebilidad;

	@NotEmpty
	private String nombre;

	// bi-directional many-to-one association to Prestamo
	@OneToMany(mappedBy = "debilidad")
	private List<PokemonDebilidad> pokemonsDebilidades;

	public Long getIdDebilidad() {
		return idDebilidad;
	}

	public void setIdDebilidad(Long id) {
		this.idDebilidad = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Debilidad [idDebilidad=" + idDebilidad + ", nombre=" + nombre + "]";
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 1L;

}
