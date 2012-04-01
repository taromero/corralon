package ar.com.seminario.corralon.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonManagedReference;

@Entity
@Table(name  = "material_proveedor")
public class MaterialProveedor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3213080512936183705L;

	
	private Long id_material_proveedor;
	private Material material;
	private Proveedor proveedor;
	private Double precio;//atributo propio de la relacion
	
	public MaterialProveedor(){
		
	}
	
	public MaterialProveedor(Material material, Proveedor proveedor,
			Double precio) {
		super();
		this.material = material;
		this.proveedor = proveedor;
		this.precio = precio;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId_material_proveedor() {
		return id_material_proveedor;
	}
	public void setId_material_proveedor(Long id_material_proveedor) {
		this.id_material_proveedor = id_material_proveedor;
	}
	@OneToOne
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	
	@JsonManagedReference("proveedor-materialProveedor")
	@ManyToOne
	@JoinColumn(name = "id_proveedor")
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
}
