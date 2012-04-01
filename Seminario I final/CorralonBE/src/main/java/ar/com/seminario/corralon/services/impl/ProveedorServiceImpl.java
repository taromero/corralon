package ar.com.seminario.corralon.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.seminario.corralon.dao.ProveedorDAO;
import ar.com.seminario.corralon.domain.Proveedor;
import ar.com.seminario.corralon.services.ProveedorService;

@Service("proveedorService")
public class ProveedorServiceImpl implements ProveedorService{

	@Autowired
	private ProveedorDAO proveedorDao;
	
	@Override
	public void delete(Long id) {
		proveedorDao.delete(id);
	}

	@Override
	public List<Proveedor> findAll() {
		return proveedorDao.findAll();
	}

	@Override
	public Proveedor findByID(Long id) {
		return proveedorDao.findByID(id);
	}

	@Override
	public void persist(Proveedor entity) {
		proveedorDao.persist(entity);
	}

	@Override
	public void merge(Proveedor entity) {
		proveedorDao.merge(entity);
	}

	@Override
	public Proveedor findByRazonSocial(String razonSocial) {
		return proveedorDao.findByRazonSocial(razonSocial);
	}

	@Override
	public List<String> getListadoProveedores() {
		List<String> razonesSociales = new ArrayList<String>();
		List<Proveedor> proveedores = proveedorDao.findAll();
		for(Proveedor p : proveedores){
			razonesSociales.add(p.getRazonSocial());
		}
		return razonesSociales;
	}

}
