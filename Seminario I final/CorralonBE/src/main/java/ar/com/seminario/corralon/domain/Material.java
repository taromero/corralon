package ar.com.seminario.corralon.domain;

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="DISC", discriminatorType=DiscriminatorType.STRING,length=20)
public abstract class Material implements Serializable{

	/*No conoce al proveedor
	 * 
	 */
	private static final long serialVersionUID = -5760666676967809538L;
	/*El material NO conoce al proveedor*/
	private Long id_material;
	private String descripcion;
	private Double precioVenta;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId_material() {
		return id_material;
	}
	@SuppressWarnings("unused")
	private void setId_material(Long id_material) {
		this.id_material = id_material;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Double getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(Double precioVenta) {
		this.precioVenta = precioVenta;
	}	
}
