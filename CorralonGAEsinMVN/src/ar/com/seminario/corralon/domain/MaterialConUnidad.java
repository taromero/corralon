package ar.com.seminario.corralon.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "UNI")
public class MaterialConUnidad extends MaterialSinUnidad {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8271884973067078240L;

	private String unidad;
	private Integer cantidad;

	public MaterialConUnidad(){}
	
	public MaterialConUnidad(MaterialConUnidad mat){
		super(mat);
		this.unidad = mat.getUnidad();
		this.cantidad = mat.getCantidad();
	}
	
	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "Material [id_material=" + getId_material()+ ", descripcion="
		+ getDescripcion()+" "+cantidad+unidad+ ", precioVenta=" + getPrecioVenta()	
		+", stock=" + getStock() + "]";
	}
}
