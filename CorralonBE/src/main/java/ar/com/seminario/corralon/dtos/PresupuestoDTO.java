package ar.com.seminario.corralon.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.seminario.corralon.domain.Cliente;
import ar.com.seminario.corralon.domain.ItemPresupuesto;
import ar.com.seminario.corralon.domain.Presupuesto;
import ar.com.seminario.corralon.domain.Usuario;

public class PresupuestoDTO {
	private Long id_presupuesto;
	private Cliente cliente;
	private Date fechaEmision;
	private Double total;
	private boolean isConfirmado;
	private Usuario usuario;//El usuario logueado que la realizo
	private List<ItemPresupuestoDTO> itemsPresupuesto = new ArrayList<ItemPresupuestoDTO>();
	
	public PresupuestoDTO(){}
	
	public PresupuestoDTO(Presupuesto presupuesto) {
		super();
		this.id_presupuesto = presupuesto.getId_presupuesto();
		this.cliente = presupuesto.getCliente();
		this.fechaEmision = presupuesto.getFechaEmision();
		this.total = presupuesto.getTotal();
		this.isConfirmado = presupuesto.getIsConfirmado();
		this.usuario = presupuesto.getUsuario();
		for(ItemPresupuesto itemPresupuesto : presupuesto.getItemsPresupuesto()){
			this.itemsPresupuesto.add(new ItemPresupuestoDTO(itemPresupuesto));
		}
	}
	public Long getId_presupuesto() {
		return id_presupuesto;
	}
	public void setId_presupuesto(Long id_presupuesto) {
		this.id_presupuesto = id_presupuesto;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
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
	public boolean isConfirmado() {
		return isConfirmado;
	}
	public void setConfirmado(boolean isConfirmado) {
		this.isConfirmado = isConfirmado;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<ItemPresupuestoDTO> getItemsPresupuesto() {
		return itemsPresupuesto;
	}
	public void setItemsPresupuesto(List<ItemPresupuestoDTO> itemsPresupuesto) {
		this.itemsPresupuesto = itemsPresupuesto;
	}
	
	
}
