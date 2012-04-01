package ar.com.seminario.corralon.domain;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonManagedReference;

import com.google.appengine.api.datastore.Key;


@Entity
public class Presupuesto implements Serializable{

	
	private static final long serialVersionUID = -2889901105373320245L;
	
	private Key id_presupuesto;
	private Cliente cliente;
	private Date fechaEmision;
	private Double total;
	private boolean isConfirmado;
	private Usuario usuario;//El usuario logueado que la realizo
	private List<ItemPresupuesto> itemsPresupuesto = new ArrayList<ItemPresupuesto>();
	
	public Presupuesto(){}
	
	public Presupuesto(Cliente cliente, Usuario usuario, boolean isConfirmado) {
		super();
		this.cliente = cliente;
		this.usuario = usuario;
		this.isConfirmado = isConfirmado;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Key getId_presupuesto() {
		return id_presupuesto;
	}
	@SuppressWarnings("unused")
	private void setId_presupuesto(Key id_presupuesto) {
		this.id_presupuesto = id_presupuesto;
	}
	
	/*Un pedido conoce a su cliente*/
	@JsonManagedReference("cliente-presupuesto")
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_cliente")
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	@Temporal(TemporalType.DATE)
	public Date getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public boolean getIsConfirmado() {
		return isConfirmado;
	}
	public void setIsConfirmado(boolean isConfirmado) {
		this.isConfirmado = isConfirmado;
	}
	
	/*Obtiene el pedido con todos sus items. Hago que las operaciones sobre el presupuesto
	 * sean en cascada (borro el presupuesto, borro sus items)*/
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) 
	@JoinColumn(name = "id_presupuesto")//La fk de la otra tabla con la cual hara join
	public List<ItemPresupuesto> getItemsPresupuesto() {
		return itemsPresupuesto;
	}
	public void setItemsPresupuesto(List<ItemPresupuesto> itemsPresupuesto) {
		this.itemsPresupuesto = itemsPresupuesto;
	}
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_usuario")
	public Usuario getUsuario() {
		return usuario;
	}	
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}