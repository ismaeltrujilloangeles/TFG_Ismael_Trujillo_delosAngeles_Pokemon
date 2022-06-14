package com.ejemplos.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;

@Entity
@Table(name = "pokemonsCombates")
public class PokemonCombates implements Serializable {

	@Id
	private Long idPokemon;

	@NotEmpty
	private Long HP;
	
	@NotEmpty
	private Long attack;
	
	@NotEmpty
	private Long defense;
	
	@NotEmpty
	private Long spattack;
	
	@NotEmpty
	private Long spdefense;
	
	@NotEmpty
	private Long speed;
	
	public Long getIdPokemon() {
		return idPokemon;
	}

	public void setIdPokemon(Long idPokemon) {
		this.idPokemon = idPokemon;
	}

	public Long getHP() {
		return HP;
	}

	public void setHP(Long hp) {
		this.HP = hp;
	}

	public Long getAttack() {
		return attack;
	}

	public void setAttack(Long attack) {
		this.attack = attack;
	}
	
	public Long getDefense() {
		return defense;
	}

	public void setDefense(Long defense) {
		this.defense = defense;
	}
	
	public Long getSpattack() {
		return spattack;
	}

	public void setSpattack(Long spattack) {
		this.spattack = spattack;
	}
	
	public Long getSpdefense() {
		return spdefense;
	}

	public void setSpdefense(Long spdefense) {
		this.spdefense = spdefense;
	}
	
	public Long getSpeed() {
		return speed;
	}

	public void setSpeed(Long speed) {
		this.speed = speed;
	}

	@Override
	public String toString() {
		return "PokemonCombates [idPokemon=" + idPokemon + ", HP=" + HP + ", attack=" + attack + ", defense=" + defense
				+ ", spattack=" + spattack + ", spdefense=" + spdefense + ", speed=" + speed + "]";
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 1L;

}
