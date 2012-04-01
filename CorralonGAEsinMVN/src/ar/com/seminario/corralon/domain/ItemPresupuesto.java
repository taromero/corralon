package ar.com.seminario.corralon.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.google.appengine.api.datastore.Key;

@Entity
@Table(name  = "item_presupuesto")
public class ItemPresupuesto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1499488802529843839L;
	/*El item conoce al presupuesto y al material*/
	private Key id_item_presupuesto;
	private Integer cantidad;
	private Material material;
	
	public ItemPresupuesto() {}
	//Si ponemos un constructor con parametros, tenemos que definir el constructor por defecto. 
	
	public ItemPresupuesto(Material mat, int cant) {
		material = mat;
		cantidad = cant;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Key getId_item_presupuesto() {
		return id_item_presupuesto;
	}
	public void setId_item_presupuesto(Key id_item_presupuesto) {
		this.id_item_presupuesto = id_item_presupuesto;
	}
	
	public Integer getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	
}