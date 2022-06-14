package com.ejemplos.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;

@Entity
@Table(name = "registros")
public class Registro implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private long idUsuario;
	
	@NotEmpty
	private String nombreUsuario;

	@NotEmpty
	private String resultado;

	@NotNull
	private long idPokemon;
	
	@NotEmpty
	private String nombrePokemon;

	@NotNull
	private long idPokemonCPU;
	
	@NotEmpty
	private String nombrePokemonCPU;


	public Registro() {
		super();
	}

	public Registro(@NotNull long idUusuario, @NotEmpty String nombreUsuario, @NotEmpty String resultado,
			@NotNull long idPokemon, @NotEmpty String nombrePokemon, @NotNull long idPokemonCPU,
			@NotEmpty String nombrePokemonCPU) {
		super();
		this.idUsuario = idUusuario;
		this.nombreUsuario = nombreUsuario;
		this.resultado = resultado;
		this.idPokemon = idPokemon;
		this.nombrePokemon = nombrePokemon;
		this.idPokemonCPU = idPokemonCPU;
		this.nombrePokemonCPU = nombrePokemonCPU;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getIdUusuario() {
		return idUsuario;
	}

	public void setIdUusuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public long getIdPokemon() {
		return idPokemon;
	}

	public void setIdPokemon(long idPokemon) {
		this.idPokemon = idPokemon;
	}

	public String getNombrePokemon() {
		return nombrePokemon;
	}

	public void setNombrePokemon(String nombrePokemon) {
		this.nombrePokemon = nombrePokemon;
	}

	public long getIdPokemonCPU() {
		return idPokemonCPU;
	}

	public void setIdPokemonCPU(long idPokemonCPU) {
		this.idPokemonCPU = idPokemonCPU;
	}

	public String getNombrePokemonCPU() {
		return nombrePokemonCPU;
	}

	public void setNombrePokemonCPU(String nombrePokemonCPU) {
		this.nombrePokemonCPU = nombrePokemonCPU;
	}

	@Override
	public String toString() {
		return "Registro [id=" + id + ", idUusuario=" + idUsuario + ", nombreUsuario=" + nombreUsuario + ", resultado="
				+ resultado + ", idPokemon=" + idPokemon + ", nombrePokemon=" + nombrePokemon + ", idPokemonCPU="
				+ idPokemonCPU + ", nombrePokemonCPU=" + nombrePokemonCPU + "]";
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 1L;

}
