package ar.com.seminario.corralon.exceptions;

import ar.com.seminario.corralon.domain.MaterialSinUnidad;

public class StockInsuficienteException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5788217759119314526L;
	
	private MaterialSinUnidad material;
	private Integer cantidadRequerida;
	private String message = "No hay stock suficiente para el material: ";
	
	public StockInsuficienteException(MaterialSinUnidad material, Integer cantidadRequerida) {
		super();
		this.material = material;
		this.cantidadRequerida = cantidadRequerida;
	}
	
	public Integer getCantidadRequerida() {
		return cantidadRequerida;
	}

	public void setCantidadRequerida(Integer cantidadRequerida) {
		this.cantidadRequerida = cantidadRequerida;
	}

	public String getMessage() {
		return message + this.getMaterial().getDescripcion() + 
				".La cantidad solicitada fue de " + this.getCantidadRequerida() + ", pero solo hay " + 
				this.getMaterial().getStock() + " en stock.";
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MaterialSinUnidad getMaterial() {
		return material;
	}

	public void setMaterial(MaterialSinUnidad material) {
		this.material = material;
	}

}
