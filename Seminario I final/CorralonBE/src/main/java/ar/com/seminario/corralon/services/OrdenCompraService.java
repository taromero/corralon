package ar.com.seminario.corralon.services;

import java.util.Date;
import java.util.List;

import ar.com.seminario.corralon.common.service.GenericService;
import ar.com.seminario.corralon.domain.Madera;
import ar.com.seminario.corralon.domain.OrdenCompra;
import ar.com.seminario.corralon.exceptions.UsuarioInexistenteException;

public interface OrdenCompraService extends GenericService<OrdenCompra> {

	public void generarOrdenCompra(Long idUsuario, String razonSocialProveedor,
			List<String> descripciones, List<String> cantidades, Date fechaEntrega, String formaPago, List<Madera> maderas) throws UsuarioInexistenteException;

	public List<OrdenCompra> findByFechaAndProveedor(Date f , String proveedor);
}
