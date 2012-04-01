package ar.com.seminario.corralon.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="NORM")
public class MaterialSinUnidad extends Material {

	/**
	 * 
	 */
	private static final long serialVersionUID = 367596754768259964L;

	private Integer stock;

	public MaterialSinUnidad(){}
	
	public MaterialSinUnidad(Integer stock) {
		super();
		this.stock = stock;
	}

	public MaterialSinUnidad(MaterialSinUnidad mat){
		super(mat);
		this.stock = mat.getStock();
	}
	
	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getStock() {
		return stock;
	}

	@Override
	public String toString() {
		return "Material [id_material=" + getId_material() + ", descripcion="
		+ getDescripcion() + ", precioVenta=" + getPrecioVenta() +", stock=" + stock + "]";
	}
	
	
}
