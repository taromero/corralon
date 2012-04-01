package ar.com.seminario.corralon.services;

import java.util.List;

import ar.com.seminario.corralon.common.service.GenericService;
import ar.com.seminario.corralon.domain.Proveedor;

public interface ProveedorService extends GenericService<Proveedor>{
	public Proveedor findByRazonSocial(String razonSocial);
	public List<String> getListadoProveedores();
}
