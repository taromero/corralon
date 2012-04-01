package ar.com.seminario.corralon.exceptions;

import ar.com.seminario.corralon.domain.MaterialSinUnidad;

public class StockInsuficienteException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5788217759119314526L;
	
	private MaterialSinUnidad material;

	public StockInsuficienteException(MaterialSinUnidad material) {
		super();
		this.material = material;
	}

	public MaterialSinUnidad getMaterial() {
		return material;
	}

	public void setMaterial(MaterialSinUnidad material) {
		this.material = material;
	}

}
