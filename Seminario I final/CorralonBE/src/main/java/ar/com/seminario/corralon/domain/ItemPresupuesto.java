package ar.com.seminario.corralon.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;

@Entity
@Table(name  = "item_presupuesto")
public class ItemPresupuesto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1499488802529843839L;
	/*El item conoce al presupuesto y al material*/
	private Long id_item_presupuesto;
	private Integer cantidad;
	private Presupuesto presupuesto;
	private Material material;
	
	public ItemPresupuesto() {}
	//Si ponemos un constructor con parametros, tenemos que definir el constructor por defecto. 
	
	public ItemPresupuesto(Material mat, int cant) {
		material = mat;
		cantidad = cant;
	}
	
	public ItemPresupuesto(Material mat, int cant, Presupuesto presupuesto) {
		this.material = mat;
		this.cantidad = cant;
		this.presupuesto = presupuesto;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId_item_presupuesto() {
		return id_item_presupuesto;
	}
	public void setId_item_presupuesto(Long id_item_presupuesto) {
		this.id_item_presupuesto = id_item_presupuesto;
	}
	
	public Integer getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	@JsonBackReference("presupuesto-item") 
	@ManyToOne
	@JoinColumn(name = "id_presupuesto")
	public Presupuesto getPresupuesto() {
		return presupuesto;
	}
	public void setPresupuesto(Presupuesto presupuesto) {
		this.presupuesto = presupuesto;
	}
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	
}