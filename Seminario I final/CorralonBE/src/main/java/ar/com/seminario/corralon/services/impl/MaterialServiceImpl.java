package ar.com.seminario.corralon.services.impl;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.seminario.corralon.dao.MaterialDAO;
import ar.com.seminario.corralon.domain.Madera;
import ar.com.seminario.corralon.domain.Material;
import ar.com.seminario.corralon.domain.MaterialConUnidad;
import ar.com.seminario.corralon.domain.MaterialProveedor;
import ar.com.seminario.corralon.domain.MaterialSinUnidad;
import ar.com.seminario.corralon.exceptions.StockInsuficienteException;
import ar.com.seminario.corralon.services.MaterialService;

@Service("materialService")
public class MaterialServiceImpl implements MaterialService{

	@Autowired
	private MaterialDAO materialDAO;

	@Override
	public Material findByDescripcion(String desc) {
		return materialDAO.findMaterialByDescripcion(desc);
	}
	
	@Override
	public Madera findByDescripcionYLargo(String desc, Float largo) {
		return materialDAO.findMaterialByDescripcionYLargo(desc, largo);
	}
	
	@Override
	public void delete(Long id) {
		materialDAO.delete(id);
	}

	@Override
	public List<Material> findAll() {
		return materialDAO.findAll();
	}

	@Override
	public Material findByID(Long id) {
		return materialDAO.findByID(id);
	}

	@Override
	public void persist(Material entity) {
		materialDAO.persist(entity);
	}
	
	@Override
	public Long save(Material entity) {
		return (Long) materialDAO.save(entity);
	}
	
	public void merge(Material entity) {
		materialDAO.merge(entity);
	}
	
	public int obtenerStockMadera (String desc, float largo){
		int stock = materialDAO.obtenerStockMadera(desc, largo);
		return stock;
	}

	public List<Madera> obtenerMaderasAdecuadas (String desc, float largo) {
		List<Madera> madAdecuadas = materialDAO.obtenerMaderasAdecuadas(desc,largo);
		return madAdecuadas;
	}
	
	@Transactional
	public Madera cortarMadera (Madera maderaMadre,float largo){
		Madera maderaHija = new Madera();
		maderaHija.setDescripcion(maderaMadre.getDescripcion());
		maderaHija.setEnPresupuesto(true);
		maderaHija.setLargo(largo);
		maderaHija.setMaderaMadre(maderaMadre);
		maderaHija.setPrecioVenta(maderaMadre.getPrecioVenta());
		maderaHija.setUnidad(maderaMadre.getUnidad());
		maderaMadre.getMaderasHijas().add(maderaHija);	
		maderaMadre.setLargo(maderaMadre.getLargo()-largo);
		Long idMaderaHija = save(maderaHija);
		merge(maderaMadre);
		return (Madera) findByID(idMaderaHija);		
	}	
	
	@Transactional
	public Madera rearmarMadera (Madera maderaHija){
		Madera maderaMadre = maderaHija.getMaderaMadre();
		if(maderaMadre == null){
			return null;
		}
		if (!maderaMadre.isEnPresupuesto()){
			float largoHija, largoMadre;
			largoHija = maderaHija.getLargo();
			largoMadre = maderaMadre.getLargo();		
			maderaMadre.setLargo(largoHija + largoMadre);
			maderaMadre.getMaderasHijas().remove(maderaHija);
			maderaHija.setMaderaMadre(null);
			merge(maderaMadre);
			delete(maderaHija.getId_material());
			return maderaMadre;
		}
		else{ //La madera hija pasa a ser la madera madre de todas las hijas de su madre y de su propia madre
			maderaMadre.getMaderasHijas().remove(maderaHija);
			maderaHija.setMaderasHijas(maderaMadre.getMaderasHijas());
			maderaHija.getMaderasHijas().add(maderaMadre);
			maderaHija.setMaderaMadre(null);
			maderaHija.setEnPresupuesto(false);
			for (Madera mHermanas : maderaHija.getMaderasHijas()) { //maderaHija pasa a ser la madre de sus hermanas y de su madre
				mHermanas.setMaderaMadre(maderaHija);
			}
			maderaMadre.setMaderasHijas(new HashSet<Madera>());
			merge(maderaHija);
			return maderaHija;	
		}		
	}
	
	public List<String> findAllNonRepeatedDescriptions(){
		return materialDAO.findAllNonRepeatedDescriptions();
	}
	
	public Madera findMaterialByDescripcionLargoYMadre(String descripcionMaterial, Float largo, Long idMadre){
		return materialDAO.findMaterialByDescripcionLargoYMadre(descripcionMaterial, largo, idMadre);
	}

	@Override
	public Double calcularPrecio(Double precioLineal, Double largoMadera,
			Double largoACortar, Boolean guardarSobrante, Boolean cepillar) {
		Double precio = 0.00;
		
		if(guardarSobrante){
			precio = precioLineal * largoACortar;
		}else{
			precio = precioLineal * largoMadera;
		}
		
		if(cepillar){
			precio += precio * 10/100;
		}
		
		return precio;
	}
	
	public Double calcularPrecio(String desc){
		return materialDAO.findMaterialByDescripcion(desc).getPrecioVenta();
	}

	@Override
	public List<Material> findByProveedor(String prov) {
		return materialDAO.findByProveedor(prov);
	}

	@Override
	public Double findPrecioMatProveedor(String prov, String desc) {
		Double precio = materialDAO.findPrecioMatProveedor(prov, desc);
		return precio;
	}

	@Override
	public MaterialProveedor findMatProvByDescripcion(String descripcion) {
		return materialDAO.findMatProvByDescripcion(descripcion);
	}

	@Override
	public Boolean hasSuficienteStock(Material material, Integer cantidadRequerida) throws StockInsuficienteException {
		if(material instanceof MaterialSinUnidad){
			if(((MaterialSinUnidad)material).getStock() < cantidadRequerida){
				throw new StockInsuficienteException((MaterialSinUnidad) material);
			}
		}
		return true;
	}
	
	public String obtenerUnidad(String desc){
		Material material = materialDAO.findMaterialByDescripcion(desc);
		if(material instanceof MaterialConUnidad){
			return (((MaterialConUnidad)material).getUnidad());
		}
		else return null;
	}

	@Override
	public List<String> findDescriptionsByProveedor(String prov) {
		return materialDAO.findDescriptionsByProveedor(prov);
	}

	@Override
	public MaterialProveedor findByDescripcionYProveedor(String desc,
			String prov) {
		return materialDAO.findByDescripcionYProveedor(desc, prov);
	}

	@Override
	public String obtenerUnidadMatProv(String desc, String prov) {
		MaterialProveedor mp = materialDAO.findByDescripcionYProveedor(desc, prov);
		if(mp.getMaterial() instanceof MaterialConUnidad){
			return (((MaterialConUnidad)mp.getMaterial()).getUnidad());
		}
		else return null;
	}
}
