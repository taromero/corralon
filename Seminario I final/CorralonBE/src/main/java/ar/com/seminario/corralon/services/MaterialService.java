package ar.com.seminario.corralon.services;

import java.util.List;

import ar.com.seminario.corralon.common.service.GenericService;
import ar.com.seminario.corralon.domain.Madera;
import ar.com.seminario.corralon.domain.Material;
import ar.com.seminario.corralon.domain.MaterialProveedor;
import ar.com.seminario.corralon.exceptions.StockInsuficienteException;

public interface MaterialService extends GenericService<Material>{
	public Material findByDescripcion(String desc);
		
	/**
	 * Devuelve las maderas que poseen esa descripcion, un largo >= al propuesto y que no esten presupuestadas
	 * @param desc
	 * @param largo
	 * @return
	 */
	public List<Madera> obtenerMaderasAdecuadas (String desc, float largo);
	
	/**
	 * Retorna el stock de una cierta madera y un largo
	 * @param desc
	 * @param largo
	 * @return 
	 */
	public int obtenerStockMadera (String desc, float largo);
	
	/**
	 * Devuelve la madera hija obtenida despues de cortar la madera madre. La madera madre disminuye su largo. 
	 * @param maderaMadre
	 * @param largo
	 * @return maderaHija
	 */
	public Madera cortarMadera (Madera maderaMadre,float largo);
	
	/**
	 * Si la maderaMadre no esta presupuestada: a esta se le agrega el largo de la madera hija, esta ultima es eliminada.
	 * Si la maderaMadre esta presupuestada: la madera hija pasa a ser la madre de las hijas de su madre y de su madre misma y enPresupuesto de maderaHija pasa a ser false
	 * @param maderaHija
	 * @return devuelve la madera que queda como madre
	 */
	public Madera rearmarMadera (Madera maderaHija);

	
	public Madera findByDescripcionYLargo(String desc, Float largo);
	
	/**
	 * Trae solo las descripciones de los materiales. Al usar distinct hace que solo traiga las descripciones distintas.
	 * @return
	 */
	public List<String> findAllNonRepeatedDescriptions();
	
	/**
	 * Para traer una madera determinada por todos los datos que la distinguen
	 * @param descripcionMaterial
	 * @param largo
	 * @param idMadre
	 * @return
	 */
	public Madera findMaterialByDescripcionLargoYMadre(String descripcionMaterial, Float largo, Long idMadre);
	
	/**
	 * Calcula el precio de una madera para mostrar en el presupuesto
	 * @param precioLineal
	 * @param largoMadera
	 * @param largoACortar
	 * @param guardarSobrante
	 * @param cepillar
	 * @return
	 */
	public Double calcularPrecio(Double precioLineal, Double largoMadera, Double largoACortar, Boolean guardarSobrante, Boolean cepillar);
	
	/**
	 * Calcula el precio de un material que no sea una madera
	 * @param desc
	 * @return
	 */
	public Double calcularPrecio(String desc);
	
	public List<Material> findByProveedor(String prov);
	
	public Double findPrecioMatProveedor(String prov, String desc);
	
	public MaterialProveedor findMatProvByDescripcion(String descripcion);
	
	public Boolean hasSuficienteStock(Material material, Integer cantidadRequerida) throws StockInsuficienteException;
	
	public String obtenerUnidad(String desc);

	Long save(Material entity);
	
	/**
	 * Trae solo las descripciones de los materiales. Al usar distinct hace que solo traiga las descripciones distintas.
	 * @return
	 */
	public List<String> findDescriptionsByProveedor(String prov);
	
	public MaterialProveedor findByDescripcionYProveedor(String desc, String prov);
	
	public String obtenerUnidadMatProv(String desc, String prov);
}
