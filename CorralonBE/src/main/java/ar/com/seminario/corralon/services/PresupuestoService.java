package ar.com.seminario.corralon.services;

import java.util.Date;
import java.util.List;

import ar.com.seminario.corralon.common.service.GenericService;
import ar.com.seminario.corralon.domain.Madera;
import ar.com.seminario.corralon.domain.Presupuesto;
import ar.com.seminario.corralon.exceptions.PresupuestoSinItemsException;
import ar.com.seminario.corralon.exceptions.PresupuestosNoEncontradosException;
import ar.com.seminario.corralon.exceptions.StockInsuficienteException;
import ar.com.seminario.corralon.exceptions.UsuarioInexistenteException;

public interface PresupuestoService extends GenericService<Presupuesto> {

	/**
	 * Genera y guarda el presupuesto.
	 * 
	 * @param idUsuario
	 * @param idCliente
	 * @param materiales
	 * @param cantidades
	 * @param maderas
	 * @return 
	 * @throws UsuarioInexistenteException
	 * @throws StockInsuficienteException
	 * @throws PresupuestoSinItemsException 
	 */
	public Presupuesto generarPresupuesto(Long idUsuario, Long idCliente,
			List<String> materiales, List<Integer> cantidades, List<Madera> maderas) throws UsuarioInexistenteException, StockInsuficienteException, PresupuestoSinItemsException; 
	
	public Presupuesto confirmarPresupuesto (Long idPresupuesto);
	
	public void eliminarPresupuesto (Long idPresupuesto);
	
	/**
	 * Para buscar varios presupuestos y despues elegir en la vista
	 */
	public List<Presupuesto> findByFechaAndCliente(Date fecha, String cliente) throws PresupuestosNoEncontradosException;
	
	public Presupuesto findUltimoGenerado();
}
