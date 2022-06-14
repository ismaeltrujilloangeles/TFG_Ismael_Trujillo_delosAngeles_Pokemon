package com.ejemplos.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.*;

@Entity
@Table(name = "pokemons")
public class Pokemon implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPokemon;

	@NotEmpty
	private String nombre;

	@NotEmpty
	private String descripcion;

	@NotEmpty
	private String imagen;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_categoria")
	private Categoria idCategoria;

	// bi-directional many-to-one association to PokemonTipo
	@OneToMany(mappedBy = "pokemon")
	private List<PokemonDebilidad> pokemonsDebilidades;

	// bi-directional many-to-one association to PokemonTipo
	@OneToMany(mappedBy = "pokemon")
	private List<PokemonHabilidad> pokemonsHabilidades;

	// bi-directional many-to-one association to PokemonTipo
	@OneToMany(mappedBy = "pokemon")
	private List<PokemonTipo> pokemonsTipos;

	public Long getId() {
		return idPokemon;
	}

	public void setId(Long id) {
		this.idPokemon = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descipcion) {
		this.descripcion = descipcion;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Categoria getCategoria() {
		return this.idCategoria;
	}

	public void setCategoria(Categoria categoria) {
		this.idCategoria = categoria;
	}

	@Override
	public String toString() {
		return "Pokemon [idPokemon=" + idPokemon + ", nombre=" + nombre + ", descripcion=" + descripcion + ", imagen="
				+ imagen + ", Categoria=" + idCategoria + "]";
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 1L;

}
