package ar.com.seminario.corralon.dtos;

public class PrecioUnidadDTO {
	private Double precio;
	private String unidad;
	
	public PrecioUnidadDTO(){}

	public PrecioUnidadDTO(Double precio, String unidad) {
		super();
		this.precio = precio;
		this.unidad = unidad;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
}
