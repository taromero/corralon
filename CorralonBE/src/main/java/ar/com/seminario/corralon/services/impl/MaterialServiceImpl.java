package ar.com.seminario.corralon.services.impl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.seminario.corralon.dao.MaterialDAO;
import ar.com.seminario.corralon.dao.MaterialProveedorDAO;
import ar.com.seminario.corralon.domain.Madera;
import ar.com.seminario.corralon.domain.Material;
import ar.com.seminario.corralon.domain.MaterialConUnidad;
import ar.com.seminario.corralon.domain.MaterialProveedor;
import ar.com.seminario.corralon.domain.MaterialSinUnidad;
import ar.com.seminario.corralon.exceptions.MaderasNoEncontradasException;
import ar.com.seminario.corralon.exceptions.StockInsuficienteException;
import ar.com.seminario.corralon.services.MaterialService;

@Service("materialService")
public class MaterialServiceImpl implements MaterialService{

	@Autowired
	private MaterialDAO materialDAO;
	@Autowired
	private MaterialProveedorDAO materialProveedorDAO;

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
	
	public Material merge(Material entity) {
		return materialDAO.merge(entity);
	}
	
	public int obtenerStockMadera (String desc, float largo){
		int stock = materialDAO.obtenerStockMadera(desc, largo);
		return stock;
	}

	public List<Madera> obtenerMaderasAdecuadas (String desc, float largo) throws MaderasNoEncontradasException{
		List<Madera> madAdecuadas = materialDAO.obtenerMaderasAdecuadas(desc,largo);
		if(madAdecuadas.isEmpty()){
			throw new MaderasNoEncontradasException();
		}
		return madAdecuadas;
	}
	
	//@DD(desc = "Se corto la madera")
	@Transactional
	public Long cortarMadera (Madera maderaMadre,float largo){
		Madera maderaHija = new Madera();
		maderaHija.setDescripcion(maderaMadre.getDescripcion());
		maderaHija.setEnPresupuesto(true);
		maderaHija.setLargo(largo);
		maderaHija.setMaderaMadre(maderaMadre);
		maderaHija.setPrecioVenta(maderaMadre.getPrecioVenta());
		maderaHija.setUnidad(maderaMadre.getUnidad());
		maderaMadre.getMaderasHijas().add(maderaHija);	
		maderaMadre.setLargo(maderaMadre.getLargo()-largo);
		Long idMaderaHija = merge(maderaHija).getId_material();
		materialDAO.merge(maderaMadre);
		return idMaderaHija;		
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
			merge(maderaMadre);
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
	public Boolean hasSuficienteStock(Material material, Integer cantidadRequerida) throws StockInsuficienteException {
		if(material instanceof MaterialSinUnidad){
			if(((MaterialSinUnidad)material).getStock() < cantidadRequerida){
				throw new StockInsuficienteException((MaterialSinUnidad) material, cantidadRequerida);
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
		return materialProveedorDAO.findDescriptionsByProveedor(prov);
	}

	@Override
	public String obtenerUnidadMatProv(String desc, String prov) {
		MaterialProveedor mp = materialProveedorDAO.findByDescripcionYProveedor(desc, prov);
		if(mp.getMaterial() instanceof MaterialConUnidad){
			return (((MaterialConUnidad)mp.getMaterial()).getUnidad());
		}
		else return null;
	}

	@Override
	public List<Madera> obtenerMaderasDeUsuarioEnSesion(List<Long> idsMaderasPresupuestandose) {
		List<Madera> maderasPresupuestandose = new ArrayList<Madera>();
		for(Long madId : idsMaderasPresupuestandose){
			Madera m = (Madera) this.findByID(madId);
			maderasPresupuestandose.add(m);
		}
		return maderasPresupuestandose;
	}

	@Override
	public Double findPrecioMatProveedor(String prov, String desc) {
		return materialProveedorDAO.findPrecioMatProveedor(prov, desc);
	}
}
