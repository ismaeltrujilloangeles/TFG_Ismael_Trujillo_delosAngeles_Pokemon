package com.ejemplos.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
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
@Table(name = "pokemons_debilidades")
public class PokemonDebilidad implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// bi-directional many-to-one association to Libro
	@ManyToOne
	@JoinColumn(name = "idPokemon")
	private Pokemon pokemon;

	// bi-directional many-to-one association to Socio
	@ManyToOne
	@JoinColumn(name = "idDebilidad")
	private Debilidad debilidad;

	public PokemonDebilidad() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Pokemon getPokemon() {
		return pokemon;
	}

	public void setPokemon(Pokemon pokemon) {
		this.pokemon = pokemon;
	}

	public Debilidad getDebilidad() {
		return debilidad;
	}

	public void setTipo(Debilidad debilidad) {
		this.debilidad = debilidad;
	}

	@Override
	public String toString() {
		return "PokemonTipo [id=" + id + ", pokemon=" + pokemon + ", debilidad=" + debilidad + "]";
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 1L;

}
