package ar.com.seminario.corralon.dao;

import java.util.List;

import ar.com.seminario.corralon.common.dao.GenericDAO;
import ar.com.seminario.corralon.domain.Madera;
import ar.com.seminario.corralon.domain.Material;

public interface MaterialDAO extends GenericDAO<Material>{

	public Material findMaterialByDescripcion(String descripcionMaterial);
	
	/**
	 * Devuelve una madera determinada
	 * @param desc
	 * @param largo
	 * @return
	 */
	public Madera findMaterialByDescripcionYLargo(String descripcionMaterial, Float largo);
	
	/**
	 * Devuelve las maderas que poseen esa descripcion, un largo >= al propuesto y que no esten presupuestadas
	 * @param desc
	 * @param largo
	 * @return
	 */
	public List<Madera> obtenerMaderasAdecuadas(String desc, float largo);

	/**
	 * Retorna el stock de una cierta madera y un largo
	 * @param desc
	 * @param largo
	 * @return 
	 */
	public int obtenerStockMadera(String desc, float largo);
	
	public List<String> findAllNonRepeatedDescriptions();
	
	public Madera findMaterialByDescripcionLargoYMadre(String descripcionMaterial, Float largo, Long idMadre);
	
	public List<Material> findByProveedor(String prov);
	
	public List<Material> findAll();
}
