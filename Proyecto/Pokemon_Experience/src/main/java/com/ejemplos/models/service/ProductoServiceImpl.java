package com.ejemplos.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejemplos.models.dao.IProductoDao;
import com.ejemplos.models.entity.Producto;

//Una clase service está basada en el patrón de diseño Facade
// un único punto de acceso a clases DAO. Por cada clase DAO hay una clase Service
@Service
public class ProductoServiceImpl implements IProductoService {

	// Autowired es para no tener que llamar al contructor, forma parte de spring
	@Autowired
	private IProductoDao productoDao;

	// el tratamiento de las trasacciones va en la clase service (no en los DAO)
	// también en un método Service podemos usar varios métodos DAO dentro de la
	// misma transacción
	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		return (List<Producto>)productoDao.findAll();
	}

	@Override
	@Transactional
	public void delete(Long id) {
		productoDao.deleteById(id);
	}

	@Override
	@Transactional
	public void save(Producto producto) {
		productoDao.save(producto);
	}

	@Override
	@Transactional(readOnly = true) // readOnlyson lo que recibe los datos
	public Producto findOne(Long id) {
		return productoDao.findById(id).orElse(null);
	}
	
}
