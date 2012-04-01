package ar.com.seminario.corralon.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonManagedReference;

@Entity
@Table(name  = "orden_compra")
public class OrdenCompra implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3304159815637724957L;
	private Proveedor proveedor;
	private Long id_orden_compra;
	private Date fechaEmision;
	private Date fechaEntrega;
	private String formaPago;
	private Double total;
	private Usuario usuario;//El usuario logueado que la realizo
	private List<ItemOrdenCompra> itemsOrdenCompra;
	
	public OrdenCompra(){}
	
	public OrdenCompra(Proveedor proveedor, Date fechaEmision,
			Date fechaEntrega, String formaPago, Double total, Usuario usuario) {
		super();
		this.proveedor = proveedor;
		this.fechaEmision = fechaEmision;
		this.fechaEntrega = fechaEntrega;
		this.formaPago = formaPago;
		this.total = total;
		this.usuario = usuario;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId_orden_compra() {
		return id_orden_compra;
	}
	public void setId_orden_compra(Long id_orden_compra) {
		this.id_orden_compra = id_orden_compra;
	}
	
	@JsonManagedReference("proveedor-ordenCompra")
	@ManyToOne
	@JoinColumn(name = "id_proveedor")
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	public String getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	
	@JsonManagedReference("ordenCompra-item")
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) //PERSIST para la aplicacion y ALL para correr la pruebaDeInsercion 
	@JoinColumn(name = "id_orden_compra")
	public List<ItemOrdenCompra> getItemsOrdenCompra() {
		return itemsOrdenCompra;
	}
	public void setItemsOrdenCompra(List<ItemOrdenCompra> itemsOrdenCompra) {
		this.itemsOrdenCompra = itemsOrdenCompra;
	}
	@Temporal(TemporalType.DATE)
	public Date getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	public Usuario getUsuario() {
		return usuario;
	}	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Date getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
			
}
