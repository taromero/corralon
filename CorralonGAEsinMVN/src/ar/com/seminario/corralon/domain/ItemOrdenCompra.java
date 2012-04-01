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

import com.google.appengine.api.datastore.Key;

@Entity
@Table(name  = "item_orden_compra")
public class ItemOrdenCompra implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 630129942557859721L;
	private Key id_item_orden_compra;
	private Integer cantidad;
	private OrdenCompra ordenCompra;
	private MaterialProveedor material;
	
	public ItemOrdenCompra(){}
	
	
	
	public ItemOrdenCompra(Integer cantidad, OrdenCompra ordenCompra,
			MaterialProveedor material) {
		super();
		this.cantidad = cantidad;
		this.ordenCompra = ordenCompra;
		this.material = material;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Key getId_item_orden_compra() {
		return id_item_orden_compra;
	}
	public void setId_item_orden_compra(Key id_item_orden_compra) {
		this.id_item_orden_compra = id_item_orden_compra;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	@JsonBackReference("ordenCompra-item")
	@ManyToOne
	@JoinColumn(name = "id_orden_compra")
	public OrdenCompra getOrdenCompra() {
		return ordenCompra;
	}
	public void setOrdenCompra(OrdenCompra ordenCompra) {
		this.ordenCompra = ordenCompra;
	}
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	public MaterialProveedor getMaterial() {
		return material;
	}
	public void setMaterial(MaterialProveedor material) {
		this.material = material;
	}
	
}