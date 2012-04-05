package ar.com.seminario.corralon.dtos;

public class MaterialConUnidadDTO extends MaterialSinUnidadDTO{
	private String unidad;
	private Integer cantidad;
	
	public MaterialConUnidadDTO(){}

	public MaterialConUnidadDTO(String unidad, Integer cantidad) {
		super();
		this.unidad = unidad;
		this.cantidad = cantidad;
	}
	
	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
}
