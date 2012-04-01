package ar.com.seminario.corralon.dtos;

import ar.com.seminario.corralon.domain.ItemPresupuesto;
import ar.com.seminario.corralon.domain.Material;

public class ItemPresupuestoDTO {
	private Long  id_item_presupuesto;
	private Integer cantidad;
	private Material material;
	
	public ItemPresupuestoDTO(){}
	
	public ItemPresupuestoDTO(ItemPresupuesto itemPresupuesto){
		this.id_item_presupuesto = itemPresupuesto.getId_item_presupuesto();
		this.cantidad = itemPresupuesto.getCantidad();
		this.material = itemPresupuesto.getMaterial();
	}
	
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
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	
	
}
