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
import javax.persistence.Table;
import javax.validation.constraints.*;

@Entity
@Table(name = "productos")
public class Producto implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProducto;

	@NotEmpty
	private String nombre;

	@NotEmpty
	private String descripcion;

	@NotEmpty
	private String imagen;
	
	@NotEmpty
	private String url1;
	
	@NotEmpty
	private String titulo1;
	
	@NotEmpty
	private String url2;
	
	@NotEmpty
	private String titulo2;
	
	
	public Producto(String nombre, String descripcion, String imagen, String url1, String titulo1, String url2, String titulo2) {
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.imagen = imagen;
		this.url1 = url1;
		this.titulo1 = titulo1;
		this.url2 = url2;
		this.titulo2 = titulo2;
	}
	
	public Producto() {
	}
	

	public Long getId() {
		return idProducto;
	}
	
	public void setId(Long id) {
		this.idProducto = id;
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

	public String getUrl1() {
		return url1;
	}

	public void setUrl1(String url1) {
		this.url1 = url1;
	}
	
	public String getTitulo1() {
		return titulo1;
	}

	public void setTitulo1(String titulo1) {
		this.titulo1 = titulo1;
	}
	
	public String getUrl2() {
		return url2;
	}

	public void setUrl2(String url2) {
		this.url2 = url2;
	}
	
	public String getTitulo2() {
		return titulo2;
	}

	public void setTitulo2(String titulo2) {
		this.titulo2 = titulo2;
	}

	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", nombre=" + nombre + ", descripcion=" + descripcion + ", imagen=" + imagen + ", url1=" + url1 +
				", titulo1=" + titulo1 + ", url2=" + url2 + ", titulo2=" + titulo2 + "]";
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 1L;

}
